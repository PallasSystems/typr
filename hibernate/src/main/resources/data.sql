-------------------------------------------------------------------------------
-- General Purpose
-------------------------------------------------------------------------------
INSERT INTO val_string_rules (identifier, detect_regex) VALUES (655557, '\\w{1,200}');
INSERT INTO field_def (name, description) VALUES ('Unstructured (Short)', 'A block of short unstructured text, with no specific meaning');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Unstructured (Short)', 655557);

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (655558, '\\w+');
INSERT INTO field_def (name, description) VALUES ('Unstructured (Long)', 'A block of unstructured text, with no specific meaning/purpose');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Unstructured (Long)', 655558);

-------------------------------------------------------------------------------
-- Time
-------------------------------------------------------------------------------
-- ISO 8601 - Event Time Unix Time
INSERT INTO val_long_rules (identifier, maximum_value, minimum_value, unit) VALUES (691181011, 2147483647, -2147483648, 'Second');
INSERT INTO field_def (name, description) VALUES ('Event Time', 'Unix Time, the number of seconds that have elapsed since 00:00:00 Coordinated Universal Time (UTC), Thursday, 1 January 1970, minus the number of leap seconds that have taken place since then');
INSERT INTO field_def_long_rules (field_definition_domain_name, long_rules_identifier) VALUES ('Event Time', 691181011);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Event Time', 'Time');

INSERT INTO val_long_rules (identifier, maximum_value, minimum_value, unit) VALUES (691181012, 2147483647, -2147483648, 'Second');
INSERT INTO field_def (name, description) VALUES ('Interval', 'Unix Time, the number of seconds that have elapsed since 00:00:00 Coordinated Universal Time (UTC), Thursday, 1 January 1970, minus the number of leap seconds that have taken place since then');
INSERT INTO field_def_long_rules (field_definition_domain_name, long_rules_identifier) VALUES ('Interval', 691181012);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Interval', 'Time');

-------------------------------------------------------------------------------
-- Coordinates
-------------------------------------------------------------------------------
-- Decimal Degree latitude
INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (1000, 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 90.0, -90.0, 'Degree Angle');
INSERT INTO field_def (name, acronym, description) VALUES ('Latitude', 'Lat', 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Latitude', 1000);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Latitude', 'Location');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Latitude', 'Air');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Latitude', 'Land');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Latitude', 'Sub-surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Latitude', 'Surface');
-- Decimal Degree Longitude
INSERT INTO val_double_rules (identifier, description, maximum_value, minimum_value, unit) VALUES (1001, 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.', 180.0, -180.0, 'Degree Angle');
INSERT INTO field_def (name, acronym, description) VALUES ('Longitude', 'Lon', 'Decimal degrees (DD) is a notation for expressing latitude and longitude geographic coordinates as decimal fractions of a degree.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Longitude', 1001);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Longitude', 'Location');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Longitude', 'Air');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Longitude', 'Land');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Longitude', 'Sub-surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Longitude', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (7210010384, 0, 360, 'Degree Angle');
INSERT INTO field_def (name, acronym, description) VALUES ('Heading (True North)', 'hdg', 'Heading based on the geographic north pole (this is fixed).');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Heading (True North)', 7210010384);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (True North)', 'Location');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (True North)', 'Air');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (True North)', 'Land');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (True North)', 'Sub-surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (True North)', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (7210010377, 0, 360, 'Degree Angle');
INSERT INTO field_def (name, acronym, description) VALUES ('Heading (Magnetic)', 'hdg', 'Heading based Magnetic North (this wanders).');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Heading (Magnetic)', 7210010377);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (Magnetic)', 'Location');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (Magnetic)', 'Air');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (Magnetic)', 'Land');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (Magnetic)', 'Sub-surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Heading (Magnetic)', 'Surface');


-------------------------------------------------------------------------------
-- AIS Data Section
-------------------------------------------------------------------------------
-- Maritime Mobile Service Identity
INSERT INTO val_string_rules (identifier, detect_regex) VALUES (115115123111, '((((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{6})|((0|8)((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{5})|((00|99|98)((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{4})|(111((2(0[1-9]|[1-9][0-9]))|([3-6][0-9]{2})|(7([0-6][0-9]|7[0-5])))[0-9]{3})|((970|972|974)[0-9]{6}))');
INSERT INTO field_def (name, acronym, description) VALUES ('Maritime Mobile Service Identity', 'MMSI', 'A Maritime Mobile Service Identity (MMSI) is effectively a maritime objects international maritime telephone number, a temporarily assigned UID, issued by that objects current flag state (unlike an IMO number, which is a global forever UID).');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Maritime Mobile Service Identity', 115115123111);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Maritime Mobile Service Identity', 'Edge');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (837971, 10000, -100, 'Knot');
INSERT INTO field_def (name, acronym, description) VALUES ('Speed Over Ground', 'SOG', 'Speed over Ground (SOG) is the vessels speed in one hour concerning the land or any other fixed object such as buoys.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Speed Over Ground', 837971);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Speed Over Ground', 'Sub-surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Speed Over Ground', 'Surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Speed Over Ground', 'Velocity');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (838487, 10000, -100, 'Knot');
INSERT INTO field_def (name, acronym, description) VALUES ('Speed Through Water', 'STW', 'Speed through Water (STW) is the vessels speed in one hour concerning the water or anything floating on water.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Speed Through Water', 838487);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Speed Through Water', 'Surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Speed Through Water', 'Velocity');

INSERT INTO val_enum_rules (identifier) VALUES (806567);
INSERT INTO enum_validation_rule_domain_enumerates (enum_validation_rule_domain_identifier, enumerates) VALUES (806567, '0');
INSERT INTO enum_validation_rule_domain_enumerates (enum_validation_rule_domain_identifier, enumerates) VALUES (806567, '1');
INSERT INTO field_def (name, acronym, description) VALUES ('Position Accuracy', 'PAC', 'Position Accuracy 0 – low accuracy 1 – high accuracy.');
INSERT INTO field_def_enum_rules (field_definition_domain_name, enum_rules_identifier) VALUES ('Position Accuracy', 806567);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Position Accuracy', 'Location');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (827984, 720, -720, 'Degree Angle per Minute');
INSERT INTO field_def (name, acronym, description) VALUES ('Rate of Turn', 'ROT', 'right or left, 0 to 720 degrees per minute (input from rate-of-turn indicator).');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Rate of Turn', 827984);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Rate of Turn', 'Surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Rate of Turn', 'Velocity');

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (737779, '(IMO)\\d{5,7}');
INSERT INTO field_def (name, acronym, description) VALUES ('International Maritime Organisation Number', 'IMO Number', 'The IMO ship identification number scheme was introduced in 1987 through adoption of resolution A.600(15), as a measure aimed at enhancing "maritime safety, and pollution prevention and to facilitate the prevention of maritime fraud".');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('International Maritime Organisation Number', 737779);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('International Maritime Organisation Number', 'Edge');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('International Maritime Organisation Number', 'Surface');

INSERT INTO val_long_rules (identifier, maximum_value, minimum_value) VALUES (7897118115, 0, 15);
INSERT INTO field_def (name, acronym, description) VALUES ('AIS Navigation Status', 'NAVSTAT', 'AIS navigational status');
INSERT INTO field_def_long_rules (field_definition_domain_name, long_rules_identifier) VALUES ('AIS Navigation Status', 7897118115);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('AIS Navigation Status', 'Status');

INSERT INTO val_long_rules (identifier, maximum_value, minimum_value) VALUES (7897228225, 0, 99);
INSERT INTO field_def (name, description) VALUES ('AIS Ship Type Codes', 'A vessels type can be deducted using the information contained in the AIS-transmitted messages that she is emitting. The vessels crew or the accountable officer are responsible for correctly entering this piece of information to the vessels AIS transponder. ');
INSERT INTO field_def_long_rules (field_definition_domain_name, long_rules_identifier) VALUES ('AIS Ship Type Codes', 7897228225);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('AIS Ship Type Codes', 'Surface');

INSERT INTO val_enum_rules (identifier) VALUES (65838267);
INSERT INTO enum_validation_rule_domain_enumerates (enum_validation_rule_domain_identifier, enumerates) VALUES (65838267, 'SAT');
INSERT INTO enum_validation_rule_domain_enumerates (enum_validation_rule_domain_identifier, enumerates) VALUES (65838267, 'TER');
INSERT INTO field_def (name, description) VALUES ('AIS Source', 'Source of AIS data ');
INSERT INTO field_def_enum_rules (field_definition_domain_name, enum_rules_identifier) VALUES ('AIS Source', 65838267);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('AIS Source', 'Surface');

INSERT INTO val_time_rules (identifier, time_pattern) VALUES (698465, 'MM-dd HH:mm');
INSERT INTO field_def (name, acronym, description) VALUES ('Estimated Time of Arrival', 'ETA', 'Estimated time of Arrival at a destination - month, day, hour, and minute in UTC');
INSERT INTO field_def_time_rules (field_definition_domain_name, time_rules_identifier) VALUES ('Estimated Time of Arrival', 698465);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Estimated Time of Arrival', 'Surface');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Estimated Time of Arrival', 'Time');

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (838889, '\\w{1,20}');
INSERT INTO field_def (name, description) VALUES ('Maritime Vessel Name', 'Name of the vessel.');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Maritime Vessel Name', 838889);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Maritime Vessel Name', 'Edge');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Maritime Vessel Name', 'Surface');

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (848859, '\\w{2}\\s\\w{1,7}[>]+\\w{2}\\s\\w{1,7}');
INSERT INTO field_def (name, description) VALUES ('UN/LOCODE', 'Unique code assigned by the UN to all ports, more information can be found: https://unece.org/trade/cefact/unlocode-code-list-country-and-territory.');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('UN/LOCODE', 848859);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('UN/LOCODE', 'Surface');

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (738889, '\\w{2,7}');
INSERT INTO field_def (name, description) VALUES ('Maritime Call Sign', 'Maritime call signs are call signs assigned as unique identifiers to ships and boats. All radio transmissions must be individually identified by the call sign.');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Maritime Call Sign', 738889);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Maritime Call Sign', 'Edge');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Maritime Call Sign', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (949387, 1000, 0, 'Metre');
INSERT INTO field_def (name, acronym, description) VALUES ('Dimension to Bow', 'A', 'Dimension (meters) from AIS GPS antenna to the Bow of the vessel.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Dimension to Bow', 949387);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Dimension to Bow', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (949398, 1000, 0, 'Metre');
INSERT INTO field_def (name, acronym, description) VALUES ('Dimension to Stern', 'B', 'Dimension (meters) from AIS GPS antenna to the Stern of the vessel (Vessel Length = A + B).');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Dimension to Stern', 949398);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Dimension to Stern', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (949489, 1000, 0, 'Metre');
INSERT INTO field_def (name, acronym, description) VALUES ('Dimension to Port', 'C', 'Dimension (meters) from AIS GPS antenna to the Port of the vessel.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Dimension to Port', 949489);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Dimension to Port', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (959390, 1000, 0, 'Metre');
INSERT INTO field_def (name, acronym, description) VALUES ('Dimension to Starboard', 'D', 'Dimension (meters) from AIS GPS antenna to the Starboard of the vessel (Vessel Width = C + D).');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Dimension to Starboard', 959390);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Dimension to Starboard', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (959391, 1000, 0, 'Metre');
INSERT INTO field_def (name, description) VALUES ('Draught', 'Current draught (meters) of the vessel.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Draught', 959391);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Draught', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (959492, 5082300000000, 0.0, 'Metre');
INSERT INTO field_def (name, description) VALUES ('Distance To Destination', 'Direct distance from current position to the destination. The maximum value is set to the distance ot Pluto');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Distance To Destination', 959492);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Distance To Destination', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value) VALUES (555260, 2147483647, 0);
INSERT INTO field_def (name, acronym, description) VALUES ('Gross Tonnage', 'GT', 'Gross tonnage (GT, G.T. or gt) is a nonlinear measure of a ships overall internal volume. Gross tonnage is different from gross register tonnage.  Neither gross tonnage nor gross register tonnage should be confused with measures of mass or weight such as deadweight tonnage or displacement. ');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Gross Tonnage', 555260);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Gross Tonnage', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value) VALUES (555261, 2147483647, 0);
INSERT INTO field_def (name, acronym, description) VALUES ('Net Tonnage', 'NT', 'Net tonnage (NT, N.T. or nt) is a dimensionless index calculated from the total moulded volume of the ships cargo spaces by using a mathematical formula.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Net Tonnage', 555261);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Net Tonnage', 'Surface');

INSERT INTO val_double_rules (identifier, maximum_value, minimum_value, unit) VALUES (555262, 2147483647, 0, 'Kilogram');
INSERT INTO field_def (name, acronym, description) VALUES ('Deadweight Tonnage', 'DWT', 'Deadweight tonnage (also known as deadweight; abbreviated to DWT, D.W.T., d.w.t., or dwt) or tons deadweight (DWT) is a measure of how much weight a ship can carry.');
INSERT INTO field_def_double_rules (field_definition_domain_name, double_rules_identifier) VALUES ('Deadweight Tonnage', 555262);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Deadweight Tonnage', 'Surface');

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (838892, '\\w{1,20}');
INSERT INTO field_def (name, description) VALUES ('Builder', 'Name of person/organisation which constructed the item');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Maritime Vessel Name', 838892);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Builder', 'Edge');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Builder', 'Surface');

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (838890, '\\w{1,20}');
INSERT INTO field_def (name, description) VALUES ('Manager', 'Person/organisation who manages a specific item of hardware/service');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Manager', 838890);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Manager', 'Edge');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Manager', 'Surface');

INSERT INTO val_string_rules (identifier, detect_regex) VALUES (838891, '\\w{1,20}');
INSERT INTO field_def (name, description) VALUES ('Owner', 'Person/organisation who owns a specific item of hardware/service');
INSERT INTO field_def_string_rules (field_definition_domain_name, string_rules_identifier) VALUES ('Maritime Vessel Name', 838891);
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Owner', 'Edge');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Owner', 'Surface');

----------------------------------------
-- Address Section
----------------------------------------
-- Setup the UK Post Code Rule
INSERT INTO val_string_rules (identifier, description, detect_regex) VALUES (12511341, 'UK Postal Code', '([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})');
INSERT INTO wrap_country_code (identifier, country_code, string_rule_identifier) VALUES (12511342, 'GBR', 12511341);

--Setup the Post Code Field Definition
INSERT INTO field_def (name, description) VALUES ('Post Code', 'Country Specific Unique code which restricts an address to a geographic area');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Post Code', 'Edge');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Post Code', 'Land');
INSERT INTO field_definition_domain_categories (field_definition_domain_name, categories) VALUES ('Post Code', 'Location');
INSERT INTO field_def_country_code_rules (field_definition_domain_name, country_code_rules_identifier) VALUES ('Post Code', 12511342);