;; A configuration file for `project-config-file' for emacs.
;; The plugin will be released soon.
;; To load the configuration file, press `C-c p f l'.
;; To load the configuration file, press `C-c p f l'.

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
	  lsp-java-vmargs '("-noverify" "-Xmx4G" "-XX:+UseG1GC" "-XX:+UseStringDeduplication")
	  )

;; run project
(defun run-gradle-commands (commands terminal-name)
  "Run gradle commands using `COMMANDS' in vterm with buffer-name
`TERMINAL-NAME'. All parameters are strings"
  (vterm-run (format "builtin cd %s && ./gradlew %s" current-project commands) terminal-name))

(defrunc project-no-build (run-gradle-commands "clean runClient" "*run-mod*"))

(global-set-key (kbd "S-M-<f10>") 'run-project-no-build)

(defrunc project (run-gradle-commands "clean build runClient" "*build-run-mod*"))

(global-set-key (kbd "S-<f10>") 'run-project)

(defrunc project-datagen
  (vterm-run
   (format
	"builtin cd %s && ./gradlew runDatagen && ./gradlew clean build runClient" current-project)
   "*build-run-datagen-mod*"))

(global-set-key (kbd "S-C-M-<f10>") 'run-project-datagen)

(defrunc project-sources-regenerate
  (run-gradle-commands "clean genSources build" "*project-sources-regenerate*"))

(global-set-key (kbd "M-<f10>") 'run-project-sources-regenerate)

(defvar project-current-world "mob-spawn-test"
  "The variable specifies a world, whose void dimension data will be removed when
`run-project-remove-dimension' is executed")

(defun run-project-remove-dimension ()
  (interactive)
  (let* ((world-path-minecraft
		  (format "run/saves/%s/dimensions/void-dimension/" project-current-world))
		 (full-world-path (concat (project-get-root) world-path-minecraft)))
	(delete-directory full-world-path t))
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
