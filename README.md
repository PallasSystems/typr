# Typing Service

This contains a number of shared libraries used by the various freeman components, each one is focussed towards solving a specific problem and has documentation specific to it within its maven module.

curl -v http://localhost:8080/api/v1/types -X GET

Result:
[{"type":"StringFieldDefinitionImpl","name":"UK Post Code","description":"Postal codes used in the United Kingdom, British Overseas Territories and Crown dependencies are known as postcodes (originally, postal codes).[1] They are alphanumeric and were adopted nationally between 11 October 1959 and 1974, having been devised by the General Post Office (Royal Mail).[2] A full postcode is known as a \"postcode unit\" and designates an area with several addresses or a single major delivery point.","validationOptional":true,"regex":"^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$"}]

Insert a rescord

curl -v http://localhost:8080/api/v1/type -X PUT -H "Content-Type: application/json" -d '{"type":"StringFieldDefinitionImpl","name":"UK Post Code","description":"Postal codes used in the United Kingdom, British Overseas Territories and Crown dependencies are known as postcodes (originally, postal codes).[1] They are alphanumeric and were adopted nationally between 11 October 1959 and 1974, having been devised by the General Post Office (Royal Mail).[2] A full postcode is known as a \"postcode unit\" and designates an area with several addresses or a single major delivery point.","validationOptional":true,"regex":"^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$"}'

## Contents
- [Contributing](./docs/software_development/software_development_plan.md)