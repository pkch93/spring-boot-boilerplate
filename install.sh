#!/bin/bash

set -u

REPOSITORY_URL="https://github.com/pkch93/spring-boot-boilerplate"

abort() {
    printf "%s\n" "$@" >&2
    exit 1
}

installBoilerPlate() {
    mkdir temp
    cd temp
    git init
    git config core.sparseCheckout true
    git remote add -f origin $REPOSITORY_URL
    echo "$1/*" > .git/info/sparse-checkout
    git pull origin main
    git checkout main
    rm -rf .git
    mv spring-boot-kts ../$2
    cd ..
    rm -rf temp

    echo "finish spring-boot-boilerplate download!"
}

if [ -z "${BASH_VERSION:-}" ]
then
    abort "Bash is required to interpret this script."
fi

while getopts ":n:" opt; do
    case ${opt} in
        n )
            boilerplate_name=${OPTARG}
            ;;
        \? )
            echo "invalid options: ${OPTARG}" 1>&2
            exit 1
            ;;
        * )
            boilerplate_name=boilerplate
            ;;
    esac
done

echo "boilerplate name: $boilerplate_name"

boilerplates="spring-boot-react-kts spring-boot-kts"

select boilerplate in $boilerplates ; do
    if [ "$boilerplate" = "spring-boot-react-kts" ] ; then
        installBoilerPlate "spring-boot-react-kts" $boilerplate_name
        exit
    elif [ "$boilerplate" = "spring-boot-kts" ] ; then
        installBoilerPlate "spring-boot-kts" $boilerplate_name
        exit
    else
        abort "not supported"
    fi
done
