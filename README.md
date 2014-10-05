# criteria

A collection of classes used to representing filtering/searching criteria.

## Dependency

The project depends on the following projects of mine:

* [pom-root](https://github.com/Haixing-Hu/pom-root): which provides configuration of dependent packages for all projects.
* [java-commons](https://github.com/Haixing-Hu/commons): which provides commonly used classes and functions for my personal Java programming.

## Build

1. Checks out the codes of this project and all its depended projects;
2. Build the depended projects in the order of above (**building order is important!**).
3. Build this project.
4. All the projects are managed by [maven](http://maven.apache.org/), so the building is as easy as typing `mvn clean install`.
