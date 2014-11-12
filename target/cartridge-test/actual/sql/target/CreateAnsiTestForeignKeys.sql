--
-- Copyright 2009 Poesys Associates. All rights reserved.
--
-- Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
-- Template: SubsystemAnsiSchemaForeignKeys.vsl

-- Create all the foreign keys for the Test schema

-- Foreign keys for LeafB

ALTER TABLE LeafB ADD CONSTRAINT LeafBPkFk FOREIGN KEY (baseId) REFERENCES Middle(baseId) ON DELETE CASCADE;

-- Foreign keys for TestChild

ALTER TABLE Child ADD CONSTRAINT testParent FOREIGN KEY (testParentId) REFERENCES TestParent(testParentId) ON DELETE CASCADE;

-- Foreign keys for MapElement

ALTER TABLE MapElement ADD CONSTRAINT MapElementPkFk FOREIGN KEY (tairObjectId) REFERENCES TairObject(tairObjectId) ON DELETE CASCADE;
ALTER TABLE MapElement ADD CONSTRAINT maps FOREIGN KEY (mapId) REFERENCES Map(mapId) ON DELETE CASCADE;
ALTER TABLE MapElement ADD CONSTRAINT maps FOREIGN KEY (mapId) REFERENCES Map(mapId) ON DELETE CASCADE;
ALTER TABLE MapElement ADD CONSTRAINT polys FOREIGN KEY (polyId) REFERENCES Poly(polyId) ON DELETE CASCADE;
ALTER TABLE MapElement ADD CONSTRAINT polys FOREIGN KEY (polyId) REFERENCES Poly(polyId) ON DELETE CASCADE;

-- Foreign keys for TestChild2TestChild

ALTER TABLE TestChild2TestChild ADD CONSTRAINT down FOREIGN KEY (downChildNo, downTestParentId) REFERENCES Child(childNo, testParentId) ON DELETE CASCADE;
ALTER TABLE TestChild2TestChild ADD CONSTRAINT up FOREIGN KEY (upChildNo, upTestParentId) REFERENCES Child(childNo, testParentId) ON DELETE CASCADE;

-- Foreign keys for LeafC

ALTER TABLE LeafC ADD CONSTRAINT LeafCPkFk FOREIGN KEY (baseId) REFERENCES ConcreteMiddle(baseId) ON DELETE CASCADE;

-- Foreign keys for TestAssociationChild

ALTER TABLE TestAssociationChild ADD CONSTRAINT assocParent FOREIGN KEY (key1, key2) REFERENCES TestNaturalParent(key1, key2) ON DELETE CASCADE;
ALTER TABLE TestAssociationChild ADD CONSTRAINT zKey FOREIGN KEY (testXId, testYId) REFERENCES TestZ(testXId, testYId) ON DELETE CASCADE;

-- Foreign keys for Self4Self

ALTER TABLE Self4Self ADD CONSTRAINT children4 FOREIGN KEY (children4Child, children4Child) REFERENCES Self4(key1, key2) ON DELETE CASCADE;
ALTER TABLE Self4Self ADD CONSTRAINT parents4 FOREIGN KEY (parents4Parent, parents4Parent) REFERENCES Self4(key1, key2) ON DELETE CASCADE;

-- Foreign keys for TestExplicitKeyChild

ALTER TABLE TestExplicitKeyChild ADD CONSTRAINT explicitParent FOREIGN KEY (testParentId) REFERENCES TestParent(testParentId) ON DELETE CASCADE;

-- Foreign keys for LeafA

ALTER TABLE LeafA ADD CONSTRAINT LeafAPkFk FOREIGN KEY (baseId) REFERENCES Middle(baseId) ON DELETE CASCADE;

-- Foreign keys for TC2TC2TC2TC

ALTER TABLE TC2TC2TC2TC ADD CONSTRAINT tc1 FOREIGN KEY (tc1DownChildNo, tc1UpChildNo, tc1DownTestParentId, tc1UpTestParentId) REFERENCES TestChild2TestChild(childNo, childNo, testParentId, testParentId) ON DELETE CASCADE;
ALTER TABLE TC2TC2TC2TC ADD CONSTRAINT tc2 FOREIGN KEY (tc2DownChildNo, tc2UpChildNo, tc2DownTestParentId, tc2UpTestParentId) REFERENCES TestChild2TestChild(childNo, childNo, testParentId, testParentId) ON DELETE CASCADE;

-- Foreign keys for TestChildChild

ALTER TABLE TestChildChild ADD CONSTRAINT childParent FOREIGN KEY (childNo, testParentId) REFERENCES Child(childNo, testParentId) ON DELETE CASCADE;

-- Foreign keys for ConcreteMiddle

ALTER TABLE ConcreteMiddle ADD CONSTRAINT ConcreteMiddlePkFk FOREIGN KEY (baseId) REFERENCES Base(baseId) ON DELETE CASCADE;

-- Foreign keys for TestZ

ALTER TABLE TestZ ADD CONSTRAINT xs FOREIGN KEY (testXId) REFERENCES TestX(testXId) ON DELETE CASCADE;

-- Foreign keys for TestB

ALTER TABLE TestB ADD CONSTRAINT a FOREIGN KEY (testAId) REFERENCES TestA(testAId);

-- Foreign keys for Middle

ALTER TABLE Middle ADD CONSTRAINT MiddlePkFk FOREIGN KEY (baseId) REFERENCES Base(baseId) ON DELETE CASCADE;

-- Foreign keys for TestNaturalChild

ALTER TABLE TestNaturalChild ADD CONSTRAINT naturalParent FOREIGN KEY (key1, key2) REFERENCES TestNaturalParent(key1, key2) ON DELETE CASCADE;

-- Foreign keys for Self2Self

ALTER TABLE Self2Self ADD CONSTRAINT children2 FOREIGN KEY (children2Self2Id) REFERENCES Self2(self2Id) ON DELETE CASCADE;
ALTER TABLE Self2Self ADD CONSTRAINT parents2 FOREIGN KEY (parents2Self2Id) REFERENCES Self2(self2Id) ON DELETE CASCADE;
