#!/bin/bash

set -u

REPOSITORY_URL="https://github.com/pkch93/spring-boot-boilerplate"

abort() {
    printf "%s\n" "$@" >&2
    exit 1
}

installBoilerPlate() {
    mkdir boilerplate
    cd boilerplate
    git init
    git config core.sparseCheckout true
    git remote add -f origin $REPOSITORY_URL
    echo "$1/*" > .git/info/sparse-checkout
    git pull origin main
    git checkout main
    rm -rf .git
    mv spring-boot-kts ../
    cd ..
    rm -rf boilerplate

    echo "finish spring-boot-boilerplate download!"
}

if [ -z "${BASH_VERSION:-}" ]
then
    abort "Bash is required to interpret this script."
fi
if [ -z `command -v git --version 2>/dev/null` ]
then
    abort "Git is required to install this boilerplates"
fi

boilerplates="spring-boot-react-kts spring-boot-kts"

select boilerplate in $boilerplates ; do
    if [ "$boilerplate" = "spring-boot-react-kts" ] ; then
        installBoilerPlate "spring-boot-react-kts"
        exit
    elif [ "$boilerplate" = "spring-boot-kts" ] ; then
        installBoilerPlate "spring-boot-kts"
        exit
    else
        abort "not supported"
    fi
done
