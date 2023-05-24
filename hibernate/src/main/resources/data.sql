INSERT INTO Categories (name, description) VALUES ('Edge', 'An Edge is a unique identifier associated with a specific location/organisation/service/individual');
INSERT INTO Categories (name, description) VALUES ('Location', 'The correlates to a location');
INSERT INTO Categories (name, description) VALUES ('Velocity', 'This a way of measuring velocity');
INSERT INTO Categories (name, description) VALUES ('Air', 'This relates to fields defining air operations');
INSERT INTO Categories (name, description) VALUES ('Land', 'This relates to operations on the land');
INSERT INTO Categories (name, description) VALUES ('Space', 'This relates to operations in space');
INSERT INTO Categories (name, description) VALUES ('Sub-surface', 'This relates to operations below the surface of the water');
INSERT INTO Categories (name, description) VALUES ('Surface', 'This relates to operations on the surface of the water');

-------------------------------------------------------------------------------
-- Coordinates
-------------------------------------------------------------------------------
-- Decimal Degree latitude
INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (1000, 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 90.0, -90.0, 'Degree Angle');
INSERT INTO single_field_def (name, acronym, description, double_rule_identifier) VALUES ('Latitude', 'Lat', 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 1000);
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Latitude', 'Location');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Latitude', 'Air');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Latitude', 'Land');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Latitude', 'Sub-surface');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Latitude', 'Surface');
-- Decimal Degree Longitude
INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (1001, 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 180.0, -180.0, 'Degree Angle');
INSERT INTO single_field_def (name, acronym, description, double_rule_identifier) VALUES ('Longitude', 'Lon', 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 1001);
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Longitude', 'Location');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Longitude', 'Air');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Longitude', 'Land');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Longitude', 'Sub-surface');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Longitude', 'Surface');

-------------------------------------------------------------------------------
-- AIS Data Section
-------------------------------------------------------------------------------
-- Maritime Mobile Service Identity
INSERT INTO val_string_rules (identifier, description, detect_regex) VALUES (115115123111, 'An MMSI comprises a series of nine digits, consisting of three Maritime Identification Digits (country-codes), concatenated with a specific identifier. Whenever an object is re-flagged, a new MMSI must be assigned.', '^((((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{6})|((0|8)((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{5})|((00|99|98)((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{4})|(111((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{3})|((970|972|974)[0-9]{6}))$');
INSERT INTO single_field_def (name, acronym, description, string_rule_identifier) VALUES ('Maritime Mobile Service Identity', 'MMSI', 'A Maritime Mobile Service Identity (MMSI) is effectively a maritime objects international maritime telephone number, a temporarily assigned UID, issued by that objects current flag state (unlike an IMO number, which is a global forever UID).', 115115123111);
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Maritime Mobile Service Identity', 'Edge');

INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (837971, 'Speed over Ground, measured in knots', 10000, -100, 'KNOTS');
INSERT INTO single_field_def (name, acronym, description, double_rule_identifier) VALUES ('Speed Over Ground', 'SOG', 'Speed over Ground (SOG) is the vessels speed in one hour concerning the land or any other fixed object such as buoys.', 837971);
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Sub-surface');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Surface');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Velocity');

INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (838487, 'Speed Through Water, measured in knots', 10000, -100, 'KNOTS');
INSERT INTO single_field_def (name, acronym, description, double_rule_identifier) VALUES ('Speed Through Water', 'STW', 'Speed through Water (STW) is the vessels speed in one hour concerning the water or anything floating on water.', 838487);
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Surface');
INSERT INTO single_field_def_categories (single_validation_rule_field_definition_domain_name, categories_name) VALUES ('Speed Over Ground', 'Velocity');


----------------------------------------
-- Address Section
----------------------------------------
-- Setup the UK Post Code Rule
INSERT INTO val_string_rules (identifier, description, detect_regex) VALUES (12511341, 'Post Code .. ', '([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})');
INSERT INTO wrap_country_code (identifier, country_code, name, string_rule_identifier) VALUES (12511341, 826, 'UK Post Code', 12511341);

--Setup the Post Code Field Definition
INSERT INTO multi_field_def (name, description) VALUES ('Post Code', 'Country Specific Unique code which restricts an address to a geographic area');
INSERT INTO multi_field_def_categories (multi_validation_rule_field_definition_domain_name, categories_name) VALUES ('Post Code', 'Edge');
INSERT INTO multi_field_def_categories (multi_validation_rule_field_definition_domain_name, categories_name) VALUES ('Post Code', 'Land');
INSERT INTO multi_field_def_categories (multi_validation_rule_field_definition_domain_name, categories_name) VALUES ('Post Code', 'Location');
INSERT INTO multi_field_def_country_code_rule_wrappers (multi_validation_rule_field_definition_domain_name, country_code_rule_wrappers_identifier) VALUES ('Post Code', 12511341);