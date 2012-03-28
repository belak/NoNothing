# NoNothing

This plugin is simple, but very efficient!
With 3 permissions you can disable hunger (So you never have to eat again), disable any kind of damage or you can sprint for an infinite time.
If you do not want to use permissions, you can create a config file, see below.
Very lightweight, no files are created! (If you use permissions)

## NOTE

This readme is out of date - updates coming soon for v1.13

## Permissions

- nonothing.nohunger - Disables Hunger
- nonothing.nodamage - Never receive damage any more
- nonothing.noexhaustion - Sprint forever

## If you don't use permissions

No problem! Just type in "/nonothing loadconfig" and a configuration file will be created, in which you can turn on and off hunger, damage and exhaustion. With this solution all people with permissions do not have to use files, but additionally you don' have to use permissions. Permissions are still used with the config, so if the config disables for example nohunger, but you have the permission, you will not starve. To disable the config, just delete it and reload the plugin.

## Changelog

### Version 1.13:

- Updated to Bukkit build 1.2.4-R0.1
- This has been done in source, not actually released yet

### Version 1.12:

- Updated to Bukkit build 1.2.3-R0.1

### Version 1.11:

- Fixed ClassCastException on Food_Level_Change Event

### Version 1.1:

- Added optional config
- Removed spammy "no-permissions" messages

### Version 1.0:

- Initial release