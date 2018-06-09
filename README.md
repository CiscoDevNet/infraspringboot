# Spring-boot with libinfracheck example

**Description**: An example project that uses the `libinfracheck` example library that demonstrates using network infrastructure as part of a healthcheck for an application.

## Dependencies

* Java 1.6 +
* Gradle
* [libinfracheck](https://github.com/ciscodevnet/libinfracheck)

# Usage

* Install `libinfracheck` into your local Maven repository per the instructions in the [libinfracheck](https://github.com/ciscodevnet/libinfracheck) repo.

Then, you will need to update the `application.yml` with the a pathId that you'd like to use:

```
apicem:
  traceId: 3772b101-6769-4dd7-9fbf-cdd45d8971ea
```

You can login (devnetuser/Cisco123!) to the [DevNet DNAC Sandbox](https://sandboxdnac.cisco.com) to find a pathId of your choosing.  Look under Flow Analysis.

Gradle:
* `gradle bootRun`

Maven: 
* `mvn spring-boot:run`

## Known issues

* Lombok throws error when running under Java 9.

## Getting help

If you have questions, concerns, bug reports, etc, please file an issue in this repository's Issue Tracker.

## License
[LICENSE](LICENSE)

## Getting involved

* If you'd like to make contributions, feel free to make a request in the issue tracker.  If you're interested in creating a Cisco DevNet Learning Lab, contact Ashley Roach (asroach at cisco.com).

## Credits and references

* Thanks to [Ali Moghadam](https://github.com/alighm) for his help with SpringBoot
* DevNet Sandbox: https://devnetsandbox.cisco.com