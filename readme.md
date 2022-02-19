# H13
Info: In Der Musterlösung sind alle Attribute public, um einfacher testen zu können. Im JaGR-Run wird diese Eigenschaft per Bytecode-Transformation erlangt, für die Manuelle Korrektur wird das Bash-Skript `letMeIiiiiiiiiiiiiiiiiiiiin.sh` bereitgestellt.

> Zum ausführen der Tests müssen die folgenden JVM-Flags gesetzt werden:
`-Djava.security.manager=allow --add-opens=java.desktop/java.awt=ALL-UNNAMED`

in VS-Code kann dazu in `settings.json` folgende Konfiguration verwendet werden:
```jsonc
{
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.completion.filteredTypes": [
    "java.awt.List",
    "com.sun.*"
  ],
  "java.debug.settings.vmArgs": "-Djava.security.manager=allow --add-opens=java.desktop/java.awt=ALL-UNNAMED",
    "java.test.config": {
    "vmArgs": [
      "-Djava.security.manager=allow",
      "--add-opens=java.desktop/java.awt=ALL-UNNAMED",
    ]
  }
}

```
