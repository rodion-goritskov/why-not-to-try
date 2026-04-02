#!/usr/bin/env sh
echo "This is script!"
./gradlew --info --stacktrace test "$@"
