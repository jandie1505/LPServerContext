# LPServerContext
Module for CloudNet v4 which automatically sets the current task name as server config option for LuckPerms.

## Requirements
- CloudNet v4 (v3 is not supported)

## Installation
1. Download the module from the releases page.
2. Put the module into your CloudNet modules directory.
3. Restart CloudNet or run the following commands in the console of CloudNet:
`modules load LPServerContext`
`modules start LPServerContext`

## How it works
This module sets the `server` context of LuckPerms to the current task id by adding `-Dluckperms.server=<taskId>` to the jvm arguments.
You just need to install the module, everything else will be handled automatically. No need for configuration.
