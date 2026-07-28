package net.balamah.voiddim.custom;

import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.io.IOException;
import java.util.UUID;
import java.net.URI;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GameProfileService {
	private static final HttpClient CLIENT = HttpClient.newHttpClient();

	public static String getPlayerUUID(
		String playerName
	) throws IOException, InterruptedException
	{
		String url = String.format(
			"https://api.minecraftservices.com/minecraft/profile/lookup/name/%s", playerName
		);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
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
		String url = String.format(
			"https://sessionserver.mojang.com/session/minecraft/profile/%s", uuidString
		);

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
		HttpResponse<String> response = CLIENT.send(
			request, HttpResponse.BodyHandlers.ofString()
		);
		if (response.statusCode() != 200) {
			return null;
		}

		GameProfile profile = new GameProfile(getMinecraftUUID(uuidString), playerName);

		JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
		JsonObject property = json.getAsJsonArray("properties").get(0).getAsJsonObject();

		profile.properties().put(
			"textures",
			new Property("textures", property.get("value").getAsString())
		);

		return profile;
	}
}
