package com.cariad.stations.models.repo

internal val mockValidChargePointsService = "[\n" +
        "    {\n" +
        "        \"DataProvider\": {\n" +
        "            \"WebsiteURL\": \"http://openchargemap.org\",\n" +
        "            \"Comments\": null,\n" +
        "            \"DataProviderStatusType\": {\n" +
        "                \"IsProviderEnabled\": true,\n" +
        "                \"ID\": 1,\n" +
        "                \"Title\": \"Manual Data Entry\"\n" +
        "            },\n" +
        "            \"IsRestrictedEdit\": false,\n" +
        "            \"IsOpenDataLicensed\": true,\n" +
        "            \"IsApprovedImport\": true,\n" +
        "            \"License\": \"Licensed under Creative Commons Attribution 4.0 International (CC BY 4.0)\",\n" +
        "            \"DateLastImported\": null,\n" +
        "            \"ID\": 1,\n" +
        "            \"Title\": \"Open Charge Map Contributors\"\n" +
        "        },\n" +
        "        \"OperatorInfo\": {\n" +
        "            \"WebsiteURL\": \"http://www.be-emobil.de/\",\n" +
        "            \"Comments\": null,\n" +
        "            \"PhonePrimaryContact\": \"+49 30 20847590\",\n" +
        "            \"PhoneSecondaryContact\": null,\n" +
        "            \"IsPrivateIndividual\": false,\n" +
        "            \"AddressInfo\": null,\n" +
        "            \"BookingURL\": null,\n" +
        "            \"ContactEmail\": \"dialog@be-emobil.de\",\n" +
        "            \"FaultReportEmail\": null,\n" +
        "            \"IsRestrictedEdit\": false,\n" +
        "            \"ID\": 214,\n" +
        "            \"Title\": \"be emobil\"\n" +
        "        },\n" +
        "        \"UsageType\": {\n" +
        "            \"IsPayAtLocation\": false,\n" +
        "            \"IsMembershipRequired\": true,\n" +
        "            \"IsAccessKeyRequired\": true,\n" +
        "            \"ID\": 4,\n" +
        "            \"Title\": \"Public - Membership Required\"\n" +
        "        },\n" +
        "        \"StatusType\": {\n" +
        "            \"IsOperational\": true,\n" +
        "            \"IsUserSelectable\": true,\n" +
        "            \"ID\": 50,\n" +
        "            \"Title\": \"Operational\"\n" +
        "        },\n" +
        "        \"SubmissionStatus\": {\n" +
        "            \"IsLive\": true,\n" +
        "            \"ID\": 200,\n" +
        "            \"Title\": \"Submission Published\"\n" +
        "        },\n" +
        "        \"UserComments\": null,\n" +
        "        \"PercentageSimilarity\": null,\n" +
        "        \"MediaItems\": null,\n" +
        "        \"IsRecentlyVerified\": false,\n" +
        "        \"DateLastVerified\": \"2017-05-08T07:17:00Z\",\n" +
        "        \"ID\": 87439,\n" +
        "        \"UUID\": \"3ED6B656-9962-470B-BF0A-B3F5306743F3\",\n" +
        "        \"ParentChargePointID\": null,\n" +
        "        \"DataProviderID\": 1,\n" +
        "        \"DataProvidersReference\": null,\n" +
        "        \"OperatorID\": 214,\n" +
        "        \"OperatorsReference\": null,\n" +
        "        \"UsageTypeID\": 4,\n" +
        "        \"UsageCost\": null,\n" +
        "        \"AddressInfo\": {\n" +
        "            \"ID\": 87785,\n" +
        "            \"Title\": \"Karl-Liebknecht-Straße\",\n" +
        "            \"AddressLine1\": \"Karl-Liebknecht-Straße 29\",\n" +
        "            \"AddressLine2\": \"Mitte\",\n" +
        "            \"Town\": \"Berlin\",\n" +
        "            \"StateOrProvince\": \"Berlin\",\n" +
        "            \"Postcode\": \"10178\",\n" +
        "            \"CountryID\": 87,\n" +
        "            \"Country\": {\n" +
        "                \"ISOCode\": \"DE\",\n" +
        "                \"ContinentCode\": \"EU\",\n" +
        "                \"ID\": 87,\n" +
        "                \"Title\": \"Germany\"\n" +
        "            },\n" +
        "            \"Latitude\": 52.524438232115585,\n" +
        "            \"Longitude\": 13.412951344982966,\n" +
        "            \"ContactTelephone1\": null,\n" +
        "            \"ContactTelephone2\": null,\n" +
        "            \"ContactEmail\": null,\n" +
        "            \"AccessComments\": \"24/7 nutzbar mit 2 kostenlosen Parkplätzen.\",\n" +
        "            \"RelatedURL\": null,\n" +
        "            \"Distance\": 0.22218678274026588,\n" +
        "            \"DistanceUnit\": 1\n" +
        "        },\n" +
        "        \"Connections\": [\n" +
        "            {\n" +
        "                \"ID\": 123951,\n" +
        "                \"ConnectionTypeID\": 25,\n" +
        "                \"ConnectionType\": {\n" +
        "                    \"FormalName\": \"IEC 62196-2 Type 2\",\n" +
        "                    \"IsDiscontinued\": false,\n" +
        "                    \"IsObsolete\": false,\n" +
        "                    \"ID\": 25,\n" +
        "                    \"Title\": \"Type 2 (Socket Only)\"\n" +
        "                },\n" +
        "                \"Reference\": null,\n" +
        "                \"StatusTypeID\": 50,\n" +
        "                \"StatusType\": {\n" +
        "                    \"IsOperational\": true,\n" +
        "                    \"IsUserSelectable\": true,\n" +
        "                    \"ID\": 50,\n" +
        "                    \"Title\": \"Operational\"\n" +
        "                },\n" +
        "                \"LevelID\": 2,\n" +
        "                \"Level\": {\n" +
        "                    \"Comments\": \"Over 2 kW, usually non-domestic socket type\",\n" +
        "                    \"IsFastChargeCapable\": false,\n" +
        "                    \"ID\": 2,\n" +
        "                    \"Title\": \"Level 2 : Medium (Over 2kW)\"\n" +
        "                },\n" +
        "                \"Amps\": 16,\n" +
        "                \"Voltage\": 400,\n" +
        "                \"PowerKW\": 11.0,\n" +
        "                \"CurrentTypeID\": 20,\n" +
        "                \"CurrentType\": {\n" +
        "                    \"Description\": \"Alternating Current - Three Phase\",\n" +
        "                    \"ID\": 20,\n" +
        "                    \"Title\": \"AC (Three-Phase)\"\n" +
        "                },\n" +
        "                \"Quantity\": 2,\n" +
        "                \"Comments\": null\n" +
        "            }\n" +
        "        ],\n" +
        "        \"NumberOfPoints\": 2,\n" +
        "        \"GeneralComments\": null,\n" +
        "        \"DatePlanned\": null,\n" +
        "        \"DateLastConfirmed\": null,\n" +
        "        \"StatusTypeID\": 50,\n" +
        "        \"DateLastStatusUpdate\": \"2017-05-08T07:17:00Z\",\n" +
        "        \"MetadataValues\": null,\n" +
        "        \"DataQualityLevel\": 1,\n" +
        "        \"DateCreated\": \"2017-05-08T07:17:00Z\",\n" +
        "        \"SubmissionStatusTypeID\": 200\n" +
        "    },\n" +
        "    {\n" +
        "        \"DataProvider\": {\n" +
        "            \"WebsiteURL\": \"https://bundesnetzagentur.de\",\n" +
        "            \"Comments\": null,\n" +
        "            \"DataProviderStatusType\": {\n" +
        "                \"IsProviderEnabled\": true,\n" +
        "                \"ID\": 20,\n" +
        "                \"Title\": \"Automated Import\"\n" +
        "            },\n" +
        "            \"IsRestrictedEdit\": false,\n" +
        "            \"IsOpenDataLicensed\": true,\n" +
        "            \"IsApprovedImport\": true,\n" +
        "            \"License\": \"Provided by Bundesnetzagentur.de under the CC-BY 4.0 license\",\n" +
        "            \"DateLastImported\": \"2021-07-12T02:21:34.507Z\",\n" +
        "            \"ID\": 29,\n" +
        "            \"Title\": \"Bundesnetzagentur.de\"\n" +
        "        },\n" +
        "        \"OperatorInfo\": {\n" +
        "            \"WebsiteURL\": \"http://www.allego.eu/\",\n" +
        "            \"Comments\": null,\n" +
        "            \"PhonePrimaryContact\": null,\n" +
        "            \"PhoneSecondaryContact\": null,\n" +
        "            \"IsPrivateIndividual\": false,\n" +
        "            \"AddressInfo\": null,\n" +
        "            \"BookingURL\": null,\n" +
        "            \"ContactEmail\": \"info@allego.eu\",\n" +
        "            \"FaultReportEmail\": null,\n" +
        "            \"IsRestrictedEdit\": false,\n" +
        "            \"ID\": 103,\n" +
        "            \"Title\": \"Allego BV\"\n" +
        "        },\n" +
        "        \"UsageType\": null,\n" +
        "        \"StatusType\": {\n" +
        "            \"IsOperational\": true,\n" +
        "            \"IsUserSelectable\": true,\n" +
        "            \"ID\": 50,\n" +
        "            \"Title\": \"Operational\"\n" +
        "        },\n" +
        "        \"SubmissionStatus\": {\n" +
        "            \"IsLive\": true,\n" +
        "            \"ID\": 100,\n" +
        "            \"Title\": \"Imported and Published\"\n" +
        "        },\n" +
        "        \"UserComments\": null,\n" +
        "        \"PercentageSimilarity\": null,\n" +
        "        \"MediaItems\": null,\n" +
        "        \"IsRecentlyVerified\": false,\n" +
        "        \"DateLastVerified\": \"2021-07-12T02:50:00Z\",\n" +
        "        \"ID\": 183437,\n" +
        "        \"UUID\": \"8B80F08D-1F60-4CB9-9BB6-3BCF75AD7954\",\n" +
        "        \"ParentChargePointID\": null,\n" +
        "        \"DataProviderID\": 29,\n" +
        "        \"DataProvidersReference\": \"994\",\n" +
        "        \"OperatorID\": 103,\n" +
        "        \"OperatorsReference\": null,\n" +
        "        \"UsageTypeID\": null,\n" +
        "        \"UsageCost\": null,\n" +
        "        \"AddressInfo\": {\n" +
        "            \"ID\": 183796,\n" +
        "            \"Title\": \"Straßburger Str. 2\",\n" +
        "            \"AddressLine1\": \"Straßburger Str. 2\",\n" +
        "            \"AddressLine2\": null,\n" +
        "            \"Town\": \"Berlin\",\n" +
        "            \"StateOrProvince\": null,\n" +
        "            \"Postcode\": \"10405\",\n" +
        "            \"CountryID\": 87,\n" +
        "            \"Country\": {\n" +
        "                \"ISOCode\": \"DE\",\n" +
        "                \"ContinentCode\": \"EU\",\n" +
        "                \"ID\": 87,\n" +
        "                \"Title\": \"Germany\"\n" +
        "            },\n" +
        "            \"Latitude\": 52.528486,\n" +
        "            \"Longitude\": 13.412186,\n" +
        "            \"ContactTelephone1\": null,\n" +
        "            \"ContactTelephone2\": null,\n" +
        "            \"ContactEmail\": null,\n" +
        "            \"AccessComments\": null,\n" +
        "            \"RelatedURL\": null,\n" +
        "            \"Distance\": 0.33563771679338417,\n" +
        "            \"DistanceUnit\": 1\n" +
        "        },\n" +
        "        \"Connections\": [\n" +
        "            {\n" +
        "                \"ID\": 290423,\n" +
        "                \"ConnectionTypeID\": 25,\n" +
        "                \"ConnectionType\": {\n" +
        "                    \"FormalName\": \"IEC 62196-2 Type 2\",\n" +
        "                    \"IsDiscontinued\": false,\n" +
        "                    \"IsObsolete\": false,\n" +
        "                    \"ID\": 25,\n" +
        "                    \"Title\": \"Type 2 (Socket Only)\"\n" +
        "                },\n" +
        "                \"Reference\": null,\n" +
        "                \"StatusTypeID\": 50,\n" +
        "                \"StatusType\": {\n" +
        "                    \"IsOperational\": true,\n" +
        "                    \"IsUserSelectable\": true,\n" +
        "                    \"ID\": 50,\n" +
        "                    \"Title\": \"Operational\"\n" +
        "                },\n" +
        "                \"LevelID\": 2,\n" +
        "                \"Level\": {\n" +
        "                    \"Comments\": \"Over 2 kW, usually non-domestic socket type\",\n" +
        "                    \"IsFastChargeCapable\": false,\n" +
        "                    \"ID\": 2,\n" +
        "                    \"Title\": \"Level 2 : Medium (Over 2kW)\"\n" +
        "                },\n" +
        "                \"Amps\": null,\n" +
        "                \"Voltage\": null,\n" +
        "                \"PowerKW\": 11.0,\n" +
        "                \"CurrentTypeID\": 20,\n" +
        "                \"CurrentType\": {\n" +
        "                    \"Description\": \"Alternating Current - Three Phase\",\n" +
        "                    \"ID\": 20,\n" +
        "                    \"Title\": \"AC (Three-Phase)\"\n" +
        "                },\n" +
        "                \"Quantity\": null,\n" +
        "                \"Comments\": null\n" +
        "            },\n" +
        "            {\n" +
        "                \"ID\": 290424,\n" +
        "                \"ConnectionTypeID\": 25,\n" +
        "                \"ConnectionType\": {\n" +
        "                    \"FormalName\": \"IEC 62196-2 Type 2\",\n" +
        "                    \"IsDiscontinued\": false,\n" +
        "                    \"IsObsolete\": false,\n" +
        "                    \"ID\": 25,\n" +
        "                    \"Title\": \"Type 2 (Socket Only)\"\n" +
        "                },\n" +
        "                \"Reference\": null,\n" +
        "                \"StatusTypeID\": 50,\n" +
        "                \"StatusType\": {\n" +
        "                    \"IsOperational\": true,\n" +
        "                    \"IsUserSelectable\": true,\n" +
        "                    \"ID\": 50,\n" +
        "                    \"Title\": \"Operational\"\n" +
        "                },\n" +
        "                \"LevelID\": 2,\n" +
        "                \"Level\": {\n" +
        "                    \"Comments\": \"Over 2 kW, usually non-domestic socket type\",\n" +
        "                    \"IsFastChargeCapable\": false,\n" +
        "                    \"ID\": 2,\n" +
        "                    \"Title\": \"Level 2 : Medium (Over 2kW)\"\n" +
        "                },\n" +
        "                \"Amps\": null,\n" +
        "                \"Voltage\": null,\n" +
        "                \"PowerKW\": 11.0,\n" +
        "                \"CurrentTypeID\": 20,\n" +
        "                \"CurrentType\": {\n" +
        "                    \"Description\": \"Alternating Current - Three Phase\",\n" +
        "                    \"ID\": 20,\n" +
        "                    \"Title\": \"AC (Three-Phase)\"\n" +
        "                },\n" +
        "                \"Quantity\": null,\n" +
        "                \"Comments\": null\n" +
        "            }\n" +
        "        ],\n" +
        "        \"NumberOfPoints\": null,\n" +
        "        \"GeneralComments\": null,\n" +
        "        \"DatePlanned\": null,\n" +
        "        \"DateLastConfirmed\": null,\n" +
        "        \"StatusTypeID\": 50,\n" +
        "        \"DateLastStatusUpdate\": \"2021-07-12T02:50:00Z\",\n" +
        "        \"MetadataValues\": [\n" +
        "            {\n" +
        "                \"ID\": 15730,\n" +
        "                \"MetadataFieldID\": 4,\n" +
        "                \"ItemValue\": \"Data from the German chargepoint registry provided by Bundesnetzagentur.de under the CC-BY 4.0 license\",\n" +
        "                \"MetadataFieldOption\": null,\n" +
        "                \"MetadataFieldOptionID\": null\n" +
        "            }\n" +
        "        ],\n" +
        "        \"DataQualityLevel\": 3,\n" +
        "        \"DateCreated\": \"2021-07-08T10:04:00Z\",\n" +
        "        \"SubmissionStatusTypeID\": 100\n" +
        "    }\n" +
        "]"