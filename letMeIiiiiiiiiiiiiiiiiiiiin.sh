#!/bin/bash
solFiles=$(find src/main/ -name '*.java')
for f in $solFiles; do
    # TODO: Unjank
    sed -i -e "s|private|public|g" "$f"
done
