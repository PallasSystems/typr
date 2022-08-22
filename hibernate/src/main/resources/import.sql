-- Generic Categories
INSERT INTO categories (name, description) VALUES('Edge', 'This field type can be ');
INSERT INTO categories (name, description) VALUES('Telephoney', 'This field type can be ');
-- Country Specific Categories
INSERT INTO categories (name, description) VALUES('UK', 'Type is specific to the UK');
INSERT INTO categories (name, description) VALUES('USA', 'Type is specific to the USA');

-- UK Specific Types
INSERT INTO string_type_definitions (name, description, detect_regex) VALUES ('Post Code', 'Postal codes used in the United Kingdom, British Overseas Territories and Crown dependencies are known as postcodes (originally, postal codes).[1] They are alphanumeric and were adopted nationally between 11 October 1959 and 1974, having been devised by the General Post Office (Royal Mail).[2] A full postcode is known as a \"postcode unit\" and designates an area with several addresses or a single major delivery point.','^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$');
INSERT INTO string_type_definitions_categories (string_field_definition_domain_name, categories_name) VALUES ('Post Code', 'UK');

-- US Specific Types
INSERT INTO string_type_definitions (name, detect_regex) VALUES ('Local Phone Number', '\\(\\d\\d\\d\\) \\d\\d\\d-?\\d\\d\\d\\d');
INSERT INTO string_type_definitions_categories (string_field_definition_domain_name, categories_name) VALUES ('Local Phone Number', 'USA');
INSERT INTO string_type_definitions (name, detect_regex) VALUES ('Date (US)', '\\d?\\d\\d?\\d/\\d\\d\\d\\d');
INSERT INTO string_type_definitions_categories (string_field_definition_domain_name, categories_name) VALUES ('Date (US)', 'USA');
INSERT INTO string_type_definitions (name, acronym, detect_regex) VALUES ('Social Security Number', 'SSN', '\\d\\d\\d-\\d\\d-\\d\\d\\d\\d');
INSERT INTO string_type_definitions_categories (string_field_definition_domain_name, categories_name) VALUES ('Social Security Number', 'Edge');
INSERT INTO string_type_definitions_categories (string_field_definition_domain_name, categories_name) VALUES ('Social Security Number', 'USA');

-- Unique Identifier Addresses
INSERT INTO string_type_definitions (name, description, detect_regex, extract_regex) VALUES ('IPv4 Address', 'Internet Protocol version 4 (IPv4) is the fourth version of the Internet Protocol (IP). It is one of the core protocols of standards-based internetworking methods in the Internet and other packet-switched networks. IPv4 was the first version deployed for production on SATNET in 1982 and on the ARPANET in January 1983. It is still used to route most Internet traffic today, even with the ongoing deployment of Internet Protocol version 6 (IPv6) its successor.','^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$', '\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b');
INSERT INTO string_type_definitions_categories (string_field_definition_domain_name, categories_name) VALUES ('IPv4 Address', 'Edge');

-- Telephoney Types
INSERT INTO string_type_definitions (name, acronym, description, detect_regex) VALUES ('Mobile Country Code', 'MCC', 'The Mobile Country Code consists of three decimal digits, The first digit of the mobile country code identifies the geographic region (the digits 1 and 8 are not used). An MCC is used in combination with an MNC (a combination known as an "MCC/MNC tuple") to uniquely identify a mobile network operator (carrier) using the GSM (including GSM-R), UMTS, LTE, and 5G public land mobile networks. Some but not all CDMA, iDEN, and satellite mobile networks are identified with an MCC/MNC tuple as well.', '^\\+[02345679]{1}[0-9]{2}$');
INSERT INTO string_type_definitions (name, acronym, description, detect_regex) VALUES ('Mobile Network Code', 'MNC', 'The Mobile Network Code consists of two or three decimal digits (for example: MNC of 001 is not the same as MNC of 01). n MCC is used in combination with an MNC (a combination known as an "MCC/MNC tuple") to uniquely identify a mobile network operator (carrier) using the GSM (including GSM-R), UMTS, LTE, and 5G public land mobile networks. Some but not all CDMA, iDEN, and satellite mobile networks are identified with an MCC/MNC tuple as well.', '^\\+[0-9]{2,3}$');