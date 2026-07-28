package net.balamah.voiddim.custom;

import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;
import java.net.URI;
import java.util.regex.Pattern;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GameProfileService {
	protected static final Pattern PLAYER_NAME_PATTERN = Pattern.compile("[A-Za-z0-9_]{3,16}");
	protected static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);
	private static final HttpClient CLIENT = HttpClient.newHttpClient();

	public static GameProfile getGameProfileByPlayerName(
		String playerName
	) throws IOException, InterruptedException
	{
		String normalizedName = normalizePlayerName(playerName);
		if (normalizedName == null) {
			return null;
		}

		String uuidString = getPlayerUUID(normalizedName);
		if (uuidString == null) {
			return null;
		}

		return getGameProfileWithProperties(uuidString, normalizedName);
	}

	public static String getPlayerUUID(
		String playerName
	) throws IOException, InterruptedException
	{
		String normalizedName = normalizePlayerName(playerName);
		if (normalizedName == null) {
			return null;
		}

		String url = String.format(
			"https://api.minecraftservices.com/minecraft/profile/lookup/name/%s", normalizedName
		);

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.timeout(REQUEST_TIMEOUT)
			.GET()
			.build();
		HttpResponse<String> response = CLIENT.send(
			request, HttpResponse.BodyHandlers.ofString()
		);
		if (response.statusCode() != 200) {
			return null;
		}

		JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

		return json.get("id").getAsString();
	}

	public static UUID getMinecraftUUID(String uuidString) {
		uuidString = uuidString.replaceFirst(
			"(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"
		);

		return UUID.fromString(uuidString);
	}

	public static GameProfile getGameProfileWithProperties(
		String uuidString, String playerName
	) throws IOException, InterruptedException
	{
		if (uuidString == null || uuidString.isBlank()) {
			return null;
		}

		String url = String.format(
			"https://sessionserver.mojang.com/session/minecraft/profile/%s", uuidString
		);

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.timeout(REQUEST_TIMEOUT)
			.GET()
			.build();
		HttpResponse<String> response = CLIENT.send(
			request, HttpResponse.BodyHandlers.ofString()
		);
		if (response.statusCode() != 200) {
			return null;
		}

		JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
		if (
			!json.has("properties")
				|| !json.get("properties").isJsonArray()
				|| json.getAsJsonArray("properties").size() == 0
		) {
			return null;
		}

		JsonObject property = json.getAsJsonArray("properties").get(0).getAsJsonObject();
		if (!property.has("value")) {
			return null;
		}

		Property textures = property.has("signature")
			? new Property(
				"textures",
				property.get("value").getAsString(),
				property.get("signature").getAsString()
			)
			: new Property("textures", property.get("value").getAsString());

		PropertyMap properties = new PropertyMap(ImmutableMultimap.of("textures", textures));

		return new GameProfile(getMinecraftUUID(uuidString), playerName, properties);
	}

	private static String normalizePlayerName(String playerName) {
		if (playerName == null) {
			return null;
		}

		String normalizedName = playerName.trim();
		return PLAYER_NAME_PATTERN.matcher(normalizedName).matches() ? normalizedName : null;
	}
}
