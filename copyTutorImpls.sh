#!/bin/bash
solFiles=$(find src/main/ -name '*.java')
for f in $solFiles; do
    fPath=$(echo "$(dirname $f)" | sed -e "s|src/main/|src/test/|g")
    mkdir -p $fPath
    cp "$f" "$fPath/$(basename $f .java)Tutor.java"
    for f2 in $solFiles; do
        sed -i -e "s|$(basename $f2 .java)|$(basename $f2 .java)Tutor|g" "$fPath/$(basename $f .java)Tutor.java"
    done
    # TODO: Unjank
    #sed -i -e "s|private|public|g" "$f"
    #sed -i -e "s|private|public|g" "$fPath/$(basename $f .java)Tutor.java"
done
