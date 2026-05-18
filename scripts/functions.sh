#!/bin/sh

printParameters() {
	cat "$0" | grep -E '^#\s*\$[0-9@#*!{}].*::' | sed 's/^# //' 
}
