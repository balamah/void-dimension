(defvar current-project (project-get-root)
  "Current project directory")

;; make java working normally
(setq lsp-java-java-path "/usr/lib/jvm/java-21-openjdk/bin/java"
	  lsp-java-import-gradle-enabled nil
	  lsp-java-import-gradle-wrapper-enabled t
	  lsp-java-import-maven-enabled nil
	  lsp-java-project-import-on-startup nil
	  lsp-java-configuration-runtimes [(:name "JavaSE-17"
										:path "/usr/lib/jvm/java-17-openjdk"
										:default t)
									   (:name "JavaSE-21"
										:path "/usr/lib/jvm/java-21-openjdk")]
	  lsp-java-vmargs '("-noverify" "-Xmx1G" "-XX:+UseG1GC"
						"-XX:+UseStringDeduplication")
	  )

;; run project
(defun run-gradle-commands (commands terminal-name)
  "Run gradle commands using `COMMANDS' in vterm with buffer-name
`TERMINAL-NAME'. All parameters are strings"
  (vterm-run (format "builtin cd %s && ./gradlew %s"
					 current-project commands) terminal-name))

(defrunc project-no-build (run-gradle-commands "clean runClient" "*run-mod*"))

(global-set-key (kbd "S-M-<f10>") 'run-project-no-build)

(defrunc project (run-gradle-commands "clean build runClient" "*build-run-mod*"))

(global-set-key (kbd "S-<f10>") 'run-project)

(defrunc project-sources-regenerate
  (run-gradle-commands "clean genSources build" "*project-sources-regenerate*"))

(global-set-key (kbd "M-<f10>") 'run-project-sources-regenerate)

(defun run-project-remove-dimension ()
  (interactive)
  (delete-directory
   (concat (project-get-root) "run/saves/mod-test-1_21_11/dimensions/void-dimension/") t)
  (message "%s Successfully removed dimension directory" (format-time-string "%H:%M"))
  (run-project))

(global-set-key (kbd "S-C-<f10>") 'run-project-remove-dimension)

;; add snippets
(let ((snippet-directory (project-config-file-directory-get-path "snippets")))
  (add-to-list 'yas-snippet-dirs 'snippet-directory)
  (yas-load-directory snippet-directory))

;; register dap-mode template
(with-eval-after-load 'dap-mode
  (dap-register-debug-template "Fabric Client (Attach)"
							   (list :type "java"
									 :request "attach"
									 :hostName "localhost"
									 :port 5005
									 :name "Fabric Client (Attach)")))
