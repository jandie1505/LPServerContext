# LPServerContext
Module for CloudNet v4 which automatically sets the current task name as server config option for LuckPerms.

## Requirements
- CloudNet v4 (v3 is not supported)
- LuckPerms configuration file in global template

## Installation
1. Download the module from the releases page.
2. Put the module into your CloudNet modules directory.
3. Restart CloudNet or run the following commands in the console of CloudNet:
`modules load LPServerContext`
`modules start LPServerContext`

## Usage
The module automatically sets the `server` config option in the LuckPerms configuration file to the task name of the respective service.  
This happens before the service starts.  
This means you don't have anything to do, except **providing a LuckPerms configuration file via the Global template.
If there is no configuration file before the start of the service, the server option will not be set!**
