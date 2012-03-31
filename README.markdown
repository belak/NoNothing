# NoNothing

This plugin aims to (eventually) be a replacement for ModifyWorld. With permissions, you can disable hunger, damage, exhaustion while sprinting, and mob targeting.
If you don't want to use permissions, you can use a config file to enable options globally.

## Permissions

- nonothing.admin - allows use of the commands
- nonothing.disable.hunger - Disables Hunger
- nonothing.disable.damage - Never receive damage any more
- nonothing.disable.exhaustion - Sprint forever
- nonothing.disable.target - Make monsters unable to see this target

Note that disabling targeting does not disable damage from mobs.
Also, some of the mobs seem to ignore this, such as spiders. This is being looked into for the next release.
The ender dragon can also still target you.

## Config

When you start NoNothing for the first time, a config file should be created. There are only a few options right now.

The main options are enable (which enables or disables the plugin) and global (which enables or disables use of the config file for all checks).
The rest of the options are only available when global is true. They are disable.hunger, disable.damage, disable.exhaustion, and disable.target.
These all work the same as permissions, but globally.

## Commands

All commands are prefixed with either nonothing, or no.

- no set [enable|global|disable-hunger|disable-damage|disable-ehxaustion|disable-target] [true|false] - sets an option in config until the server is reloaded
- no get [enable|global|hunger|damage|ehxaustion|target] - displays an option in config
- no save - saves any temporary changes with no set to the config file
- no load - loads any changes in the config file into memory

## Coming Soon

- More options to ignore mobs
- Options to change how disabling hunger works

## Changelog

### Version 1.13:

- Updated to Bukkit build 1.2.4-R0.1
- Restructured plugin
- Added admin commands
- Added an option to make most monsters not target players

### Version 1.12:

- Updated to Bukkit build 1.2.3-R0.1

### Version 1.11:

- Fixed ClassCastException on Food_Level_Change Event

### Version 1.1:

- Added optional config
- Removed spammy "no-permissions" messages

### Version 1.0:

- Initial release

## Source Code

Interested in hacking on NoNothing, or providing a patch? The source code can be found on [Github](https://github.com/belak/NoNothing).