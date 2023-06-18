# Typr
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=pallassystems-typr&metric=sqale_rating&token=b285c20ef2a5c7a14f4b983b1cd49f2cb6a12ebf)](https://sonarcloud.io/summary/new_code?id=pallassystems-typr)[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=pallassystems-typr&metric=reliability_rating&token=b285c20ef2a5c7a14f4b983b1cd49f2cb6a12ebf)](https://sonarcloud.io/summary/new_code?id=pallassystems-typr)[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pallassystems-typr&metric=coverage&token=b285c20ef2a5c7a14f4b983b1cd49f2cb6a12ebf)](https://sonarcloud.io/summary/new_code?id=pallassystems-typr)![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)

Typr is a catalog of fields types, it provides a definition for each field with validation criteria, units, and associated information links. 

Most platforms will define data schema's using primitive datatypes (e.g. int, string, char, etc..). NoSQL data stores such as [Elastic Search](https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html) provide a more comprehensive list of field types for data schema's.

This project allows systems to build on this concept, by creating a catalog that other systems can use when defining data schemas.

Systems can use this information to validate and store data for a specific field within a schema.

## Documentation

* [Overview](./docs/overview/welcome.md)
  * [Getting Started](./docs/overview/getting_started.md)
  * [Frequently Asked Questions](./docs/overview/faq.md)
* [Development](./docs/documentation/developer/index.md)
  * [Building Typr](./docs/developers/technical/building_project.md)
  * Contributing
  * [Design](./docs/documentation/developer/design.md)
  * [Releasing Typr](./docs/developers/technical/release_project.md)
* [Users](./docs/documentation/users/index.md)

## Develop and Contribute

We welcome questions, ideas, issues and code contributions to this project.

Use the [issues page](https://github.com/PallasSystems/typr/issues) to get in touch with the community.

If you would like to make a code contribution please fork the repository and create a
[GitHub pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests) to the `main` branch.

## License
![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)

See the included LICENSE file for details.
