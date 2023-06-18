-------------------------------------------------------------------------------
-- Coordinates
-------------------------------------------------------------------------------
-- Decimal Degree latitude
INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (1000, 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 90.0, -90.0, 'Degree Angle');
INSERT INTO field_def (name, acronym, description) VALUES ('Latitude', 'Lat', 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Latitude', 1000);
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Latitude', 'Location');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Latitude', 'Air');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Latitude', 'Land');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Latitude', 'Sub-surface');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Latitude', 'Surface');
-- Decimal Degree Longitude
INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (1001, 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 180.0, -180.0, 'Degree Angle');
INSERT INTO field_def (name, acronym, description) VALUES ('Longitude', 'Lon', 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Longitude', 1001);
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Longitude', 'Location');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Longitude', 'Air');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Longitude', 'Land');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Longitude', 'Sub-surface');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Longitude', 'Surface');


-------------------------------------------------------------------------------
-- AIS Data Section
-------------------------------------------------------------------------------
-- Maritime Mobile Service Identity
INSERT INTO val_string_rules (identifier, description, detect_regex) VALUES (115115123111, 'An MMSI comprises a series of nine digits, consisting of three Maritime Identification Digits (country-codes), concatenated with a specific identifier. Whenever an object is re-flagged, a new MMSI must be assigned.', '^((((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{6})|((0|8)((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{5})|((00|99|98)((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{4})|(111((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{3})|((970|972|974)[0-9]{6}))$');
INSERT INTO field_def (name, acronym, description) VALUES ('Maritime Mobile Service Identity', 'MMSI', 'A Maritime Mobile Service Identity (MMSI) is effectively a maritime objects international maritime telephone number, a temporarily assigned UID, issued by that objects current flag state (unlike an IMO number, which is a global forever UID).');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Maritime Mobile Service Identity', 115115123111);
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Maritime Mobile Service Identity', 'Edge');

INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (837971, 'Speed over Ground, measured in knots', 10000, -100, 'Knot');
INSERT INTO field_def (name, acronym, description) VALUES ('Speed Over Ground', 'SOG', 'Speed over Ground (SOG) is the vessels speed in one hour concerning the land or any other fixed object such as buoys.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Speed Over Ground', 837971);
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Sub-surface');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Surface');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Velocity');

INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (838487, 'Speed Through Water, measured in knots', 10000, -100, 'Knot');
INSERT INTO field_def (name, acronym, description) VALUES ('Speed Through Water', 'STW', 'Speed through Water (STW) is the vessels speed in one hour concerning the water or anything floating on water.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Speed Through Water', 838487);
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Surface');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Velocity');

----------------------------------------
-- Address Section
----------------------------------------
-- Setup the UK Post Code Rule
INSERT INTO val_string_rules (identifier, description, detect_regex) VALUES (12511341, 'UK Postal Code', '([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})');
INSERT INTO wrap_country_code (identifier, country_code, string_rule_identifier) VALUES (12511342, 'GBR', 12511341);

--Setup the Post Code Field Definition
INSERT INTO field_def (name, description) VALUES ('Post Code', 'Country Specific Unique code which restricts an address to a geographic area');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Post Code', 'Edge');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Post Code', 'Land');
INSERT INTO field_def_categories (field_definition_domain_name, categories_name) VALUES ('Post Code', 'Location');
INSERT INTO field_def_country_code_rules (field_definition_domain_name, country_code_rules_identifier) VALUES ('Post Code', 12511342);