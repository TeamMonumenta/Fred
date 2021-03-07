#!/bin/bash

sh compile.sh

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR/target"

plugin="$(ls -r Roguelite*.jar | grep -v sources | head -n 1)"
if [[ -z "$plugin" ]]; then
	exit 1
fi

echo "Plugin version: $plugin"

ssh -p 8822 epic@build.playmonumenta.com "cd /home/epic/dev1_shard_plugins && rm -f Roguelite*.jar"
scp -P 8822 $plugin epic@build.playmonumenta.com:/home/epic/dev1_shard_plugins/
