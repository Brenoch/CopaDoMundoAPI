#!/bin/sh
set -e

BASE_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
MAVEN_VERSION="3.9.9"
MAVEN_DIR="$BASE_DIR/.mvn"
MAVEN_HOME="$MAVEN_DIR/apache-maven-$MAVEN_VERSION"
MAVEN_ZIP="$MAVEN_DIR/apache-maven-$MAVEN_VERSION-bin.zip"
MAVEN_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip"

if [ ! -x "$MAVEN_HOME/bin/mvn" ]; then
  mkdir -p "$MAVEN_DIR"
  if [ ! -f "$MAVEN_ZIP" ]; then
    if command -v curl >/dev/null 2>&1; then
      curl -fsSL "$MAVEN_URL" -o "$MAVEN_ZIP"
    elif command -v wget >/dev/null 2>&1; then
      wget -q "$MAVEN_URL" -O "$MAVEN_ZIP"
    else
      echo "curl ou wget e necessario para baixar o Maven local." >&2
      exit 1
    fi
  fi
  unzip -q -o "$MAVEN_ZIP" -d "$MAVEN_DIR"
fi

exec "$MAVEN_HOME/bin/mvn" "$@"
