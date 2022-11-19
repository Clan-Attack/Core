# Versioning
The Plugin is versioned with

`{session}.{api}.{internal}`

## Session
The `session` version correspond to the session the plugin was last updated.
If the plugin is in the initialise state (before every used in any session) the `session` version is 0,
afterwards, when used in any session the `session` value jumps to the current session.

So when a plugin is first used in a session it's possible that the version jumps from `0.5.4` to `3.0.0`.

When the plugin was already used in a session and something's will get updated for the next session,
the `session` version is set **before** any changes for that session are made. 

## Api
The `api` version is reset to 0 every time the `session` version is changed.

If changes are made that change anything on the api, including
- adding functionality,
- removing functionality or
- changing functionality

on the "frontend" site of the api, the `api` version is increased.

## Internal
If changes are made in the implementation of the api the `internal` version is increased.

If the `api` version is increased or the `session` version set, the `internal` version is reset to 0. 
