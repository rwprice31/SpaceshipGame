BEGIN TRANSACTION;
CREATE TABLE `Weapon` (
	`weapon_id`	INTEGER,
	PRIMARY KEY(`weapon_id`)
);
CREATE TABLE `Valid_Input` (
	`valid_input_id`	INTEGER,
	PRIMARY KEY(`valid_input_id`)
);
CREATE TABLE `Suit_Part` (
	`suit_part_id`	INTEGER,
	PRIMARY KEY(`suit_part_id`)
);
CREATE TABLE `Ship_Part` (
	`ship_part_id`	INTEGER,
	PRIMARY KEY(`ship_part_id`)
);
CREATE TABLE `Room` (
	`room_id`	INTEGER,
	`monster_id`	INTEGER,
	`location_id`	INTEGER,
	`puzzle_id`	INTEGER,
	PRIMARY KEY(`room_id`),
	FOREIGN KEY(`monster_id`) REFERENCES Monster,
	FOREIGN KEY(`location_id`) REFERENCES Location,
	FOREIGN KEY(`puzzle_id`) REFERENCES Puzzle
);
CREATE TABLE `Puzzle` (
	`puzzle`	INTEGER,
	PRIMARY KEY(`puzzle`)
);
CREATE TABLE "Player" (
	`player_id`	INTEGER,
	`entity_id`	INTEGER,
	`location_id`	INTEGER,
	PRIMARY KEY(`player_id`),
	FOREIGN KEY(`entity_id`) REFERENCES `Entity`,
	FOREIGN KEY(`location_id`) REFERENCES Location
);
CREATE TABLE `Monster` (
	`monster_id`	INTEGER,
	`entity_id`	INTEGER,
	PRIMARY KEY(`monster_id`),
	FOREIGN KEY(`entity_id`) REFERENCES Entity
);
CREATE TABLE `Message_Valid_Input` (
	`valid_input_id`	INTEGER,
	`message_id`	INTEGER,
	FOREIGN KEY(`valid_input_id`) REFERENCES Valid_Input,
	FOREIGN KEY(`message_id`) REFERENCES Message
);
CREATE TABLE "Message" (
	`message_id`	INTEGER,
	`room_id`	INTEGER,
	`message_string`	TEXT,
	PRIMARY KEY(`message_id`),
	FOREIGN KEY(`room_id`) REFERENCES `Room`
);
INSERT INTO `Message` (message_id,room_id,message_string) VALUES (1,'Bedroom','You wake up laying on the floor, very confused, and it is pitch
black. What should you do?'),
 (2,'Bedroom','You are standing up'),
 (3,'Bedroom','The lamp is turned on, you see a bedroom, with a bed, a lamp,
a dresser, and an exit door.
'),
 (4,'Bedroom','You find a key and a flashlight
'),
 (5,'Bedroom',' Door opens'),
 (6,'Main Room','You are now in the central room of the spaceship, you see a
signs for the Engine Room, Decompression Room, and Cockpit, as well
as a small window to the outside'),
 (7,'Main Room',' You see that the engine for the spaceship is destroyed, you
must find 6 number of parts in order to fix it. Also, you see a crowbar'),
 (8,'Main Room',' You pick up the crowbar'),
 (9,'Main Room','You are in the main room
'),
 (10,'Cockpit','You are now standing in the cockpit. You see flight controls
and a spacesuit.'),
 (11,'Cockpit','You pick up the spacesuit'),
 (12,'Cockpit','You are now wearing the spacesuit'),
 (13,'Cockpit','You are now in the main room'),
 (14,'Decompression Room','You are now in the Decompression Room and you see that the
exit door is jammed'),
 (15,'Decompression Room','The door is now closed'),
 (16,'Decompression Room','You use the crowbar to pry open the exit door'),
 (17,'Decompression Room',' You see a barren wasteland of a planet. You will need equipment to survive.'),
 (18,'Decompression Room',' You suffocate instantly.'),
 (19,'Decompression Room
','You pry open the door leading to outside and all of the oxygen in your spaceship is instantly sucked out. With no way to replenish your oxygen, you will die a slow death.'),
 (20,'Decompression Room','The door is jammed, you need something to pry it open
'),
 (21,'Area Outside Spaceship','You see that your spaceship has crashed in the middle of a cross roads. There is are 8 roads in every direction (North, Northeast, East, Southeast, South, Southwest, West, Northwest). You also notice that there is a dune buggy next to your ship.'),
 (22,'Area Outside Spaceship','You are in the dune buggy, you notice there is a faint glowing light coming from the East road'),
 (23,'Area Outside Spaceship','You begin to walk, but quickly realize that it is too far '),
 (24,'Area Outside Spaceship',' You are back outside the spaceship'),
 (25,'Area Outside Spaceship',' An unprepared adventure into the unknown does not seem like a wise decision at this time.'),
 (26,'Jungle Entry Room','You arrive a place that appears to be a dense jungle. You are standing in a small area with 2 ways to go, deeper into the jungle or turn
back
'),
 (27,'Jungle Entry Room','There is no turning back now, you
must press on deeper into the jungle
'),
 (28,'Jungle Interior Room',' You venture further into the jungle and arrive to an area with to 3 exits. One behind you, one to the north, and one to the south.'),
 (29,'Jungle Interior Room','You head to the northern area of the jungle
'),
 (30,'Jungle Interior Room','There is no turning back now, you must press on deeper into the jungle
'),
 (31,'Jungle North Room','You arrive in the northern room. You see a locked door and what appear to be 5 lights.
'),
 (32,'Jungle North Room','You walk to the lights, there are 5 colors, Red, Blue, Green, Yellow, and Purple. Each one has a switch'),
 (33,'Jungle North Room','The red light flashes and the locked door opens slightly'),
 (34,'Jungle North Room','The blue light flashes and the locked door opens more'),
 (35,'Jungle North Room','The purple light flashes and the locked door opens halfway'),
 (36,'Jungle North Room','The blue light flashes and the locked door opens further'),
 (37,'Jungle North Room','The green light flashes and the locked door is almost completely open'),
 (38,'Jungle North Room','The yellow light flashes and the locked door is open'),
 (39,'Jungle North Room',' The door shuts before you can get there'),
 (40,'Jungle North Room','The door slams shut'),
 (41,'Jungle North Room','The door does not move'),
 (42,'Jungle Locked Room',' You enter the room through the previously locked door. You see a sword of stone hanging on the far wall.'),
 (43,'Jungle Locked Room',' You now have the stone sword
'),
 (44,'Jungle Locked Room','You are back in the North Room'),
 (45,'Jungle Locked Room',' You are now in the Interior Room.There are 3 exits. One behind you, one to the west, and one to the south.'),
 (46,'Jungle Southern Room','You enter the southern part of the jungle. There are 2 exits, one behind you and one further to the south where you hear strange noises.'),
 (47,'Jungle Monster Room','You enter the southernmost part of the jungle. You see that the Vine Monster is standing in front of you. He instantly attacks you and deals 10 damage to your spacesuit.'),
 (48,'Jungle Monster Room',' You use the stone sword to block the Vine Monster’s next attack stunning him.'),
 (49,'Jungle Monster Room',' You attack the Vine Monster, dealing him 15 damage. He is still stunned.'),
 (50,'Jungle Monster Room',' You attack the Vine Monster again. This time you manage to kill the beast. You notice that he was blocking a door.'),
 (51,'Jungle Monster Room','You are Dead.'),
 (52,'Jungle Engine Part Room','You enter the room that the Vine Monster was blocking. You notice there is what appears to be a part to your engine.
'),
 (53,'Jungle Engine Part Room','You now have the engine part'),
 (54,'Jungle Engine Part Room','You are now in the South Room
'),
 (55,'Jungle Engine Part Room','You are now in the Interior Room'),
 (56,'Jungle Engine Part Room','You are now back where you started. You notice that your spacesuit needs repairing and you are low on oxygen'),
 (57,'Jungle Engine Part Room','You are in the dune buggy'),
 (58,'Jungle Engine Part Room','You return to the spaceship to repair your suit and replenish your oxygen'),
 (59,'Jungle Engine Part Room','You need to repair your suit and replenish your oxygen levels before continuing.'),
 (60,'Jungle Random Road Monster Fight Room',' As you drive along the road, you encounter a massive monster that comes out of the road. The monster is very tall, but is not very quick.
The monster bares resemblance to a human. You are in your dune buggy, but have no chance to get away'),
 (61,'Jungle Random Road Monster Fight Room','The monster falls to a knee.'),
 (62,'Jungle Random Road Monster Fight Room','The monster falls flat on the ground, but is still not dead yet. You notice his head is at ground level.'),
 (63,'Jungle Random Road Monster Fight Room','You drive over the monster’s head. The monster has been defeated. You continue on your current path.'),
 (64,'Jungle Random Road Monster Fight Room','You exit your vehicle and are instantly squashed.'),
 (65,'Jungle Random Road Monster Fight Room','The monster picks up the road, pulls you back, and eats you'),
 (66,'Northwest Road Black Hole Monster Room','You drive down the Northwest Road and you notice things begin to become extremely dark. You realize that you are driving into the mouth of the Black Hole Monster'),
 (67,'Northwest Road Black Hole Monster Room','You exit the Dune Buggy. You are now in pitch black.
'),
 (68,'Northwest Road Black Hole Monster Room','You arrive a small metal box. It appears to be locked.
'),
 (69,'Northwest Road Black Hole Monster Room','You pray open the box and there is a circuit board inside.'),
 (70,'Northwest Road Black Hole Monster Room','You just got sucked back into the the monster and will be killed.'),
 (71,'Southwest Road ',' You drive down the southwest road and you continue driving for a long time, and you end up back at the Southeast side of spaceship.'),
 (72,'Desert','You head to the south and spot a glimpse of beige dunes with a slight red tint. As you approach though, the heat from the blazing sun forces you to turn back. It may be wise to wait until dusk to return'),
 (73,'Desert','You head south as the sun sets to your right. The red tint of the dunes glow orange as the sun fades away. You catch a silver a glimpse of a silver glint to your left. Straight ahead you spot a red canyon in the far distance. Will you go LEFT or STRAIGHT?'),
 (74,'Desert','You head left to the source of the silver gleam. You find a piece of your engine sitting atop of a pile of rocks. Will you CLIMB or TURN BACK?'),
 (75,'Desert','You start to climb the pile. Just as you reach the top and stretch out your hand for the engine part, a rock under your foot slides out. This causes you to fall and the pile of rocks to collapse. As you hit the ground, you look up in time to see your engine piece collide with your helmet. Your skull is instantly crushed.'),
 (76,'Desert','Your gut feeling tells you to return to this spot later. You return to where you came from.'),
 (77,'Desert','As you walk towards the canyon, you notice what appears to be some sort of hollow rock or a shell sticking out of the sand that is about half a foot in height. You feel like this could be convenient to carry liquid around. Will you TAKE IT or LEAVE IT?'),
 (78,'Desert','You decide to leave the shell for now and continue on your way.'),
 (79,'Desert','After a trek, you reach the foot of the canyon. There are three paths ahead of you. One leads to the left and is a relatively easy climb to the top of the canyon. The path is completely made of stone the further up it goes. Directly ahead of you is the valley of the canyon. It too is made of stone as it winds around the corner but it seems to narrow in width. It looks as if once, long ago, some liquid ran through the mountain to cut this path but the source has long since dried up. To your right, away from the canyon, is a stretch of dunes with what appears to be a lone rock in the distance. Will you go LEFT, FORWARD, or RIGHT?'),
 (80,'Desert','You hike up the slope of the canyon for a distance until you reach the summit. Once at the top, you look around. The area is barren and upon further inspection you notice a sheer drop directly in front of you. You are forced to turn back.'),
 (81,'Desert','You follow the winding, dried up river bed into the valley of the canyon. As you turn the corner, you begin to feel claustrophobic as the valley narrows to about three of your shoulder’s width. When you turn another corner following the valley, you come across a sheer wall of rock. You can imagine the waterfall that would have been formed from
such a drop had the liquid been present. Beneath the wall, you can see a shallow, smooth hole about a foot deep where the liquid would have hit. There is nothing here for you now and you are forced to turn back'),
 (82,'Desert','With no other landmark in sight, you head straight towards the lone rock in the distance. When you approach the rock, you notice strange patterns in the sand around it. As you draw closer to inspect the strange marks, you notice a strange clicking sound coming from the rock. You look up just in time to notice a shell, similar to the one you
passed, detaching itself from the rock. Unlike the previous shell, this one is home to some sort of mix between and arachnid and an amoeba. You don’t have time to escape as it attacks.'),
 (83,'Desert','You feel the ground rumble beneath your feet. You quicken your pace to a brisk jog as you continue towards the canyon'),
 (84,'Desert','As you approach the canyon, the rumbling gets louder and closer. When you reach the foot of the canyon, jaws erupt out of the sand behind you before sinking back into the sand. You now know for certain you are being hunted. There are three paths ahead of you. One leads to the left and is a relatively easy climb to the top of the canyon. The path is completely made of stone the further up it goes. Directly ahead of you is the valley of the canyon. It too is made of stone as it winds around the corner but it seems  to narrow in width. It looks as if once, long ago, some liquid ran through the mountain to cut this path but the source has long since dried up. To your right, away from the canyon, is a stretch of dunes with what appears to be a lone rock in the distance. You must chose quickly. Will you go LEFT, FORWARD, or RIGHT?'),
 (85,'Desert','Before you can set for any direction, teeth sink into your right ankle, severing your Achilles tendon. You fall forward screaming in agony. You grasp at the sand around you desperately for something to pull yourself away. Your hands are rewarded only with fine desert sand when you notice a searing pain in your leg. When you look towards your wounded limb, you look straight into the jaws of a giant, wurm-like creature. Its bottom jaw is rotating inwards with too many teeth to count, pulling you in like a wood chipper. Your screams pierce through the night for only a few moments until your suit is compromised and you choke on the planet’s atmosphere'),
 (86,'Desert','You run up the slope of the canyon until you reach the summit. Once at the top, you
desperately look around. The area is barren so you keep running. You glimpse over your shoulder to spot a wurm, roughly 20 feet long and about 5 feet wide chasing you with a wide open jaw filled with teeth. The rocky face of the canyon prevents the wurm to chase you from under the sand. You continue running in fear, not noticing the sheer drop directly in front of you until your feet no longer feel the rock beneath them. You plummet downward with only enough time to register your fall before it abruptly end with your entrails coloring the ground below a dark shade of red'),
 (87,'Desert','You bolt towards the lone rock in the distance. The wurm has dived back into the sand
behind you and you can feel the ground rumbling as it tunnels towards you. When you
approach the rock, you grab onto dent to attempt to climb it. You don’t notice until too
late the dent is actually a shell, similar to the one in your possession. Unlike the
previous shell, this one is home to some sort of mix between and arachnid and an
amoeba. You release it in surprise only to fall into the open jaws of the wurm. Crunching
is heard as your suit and bones are crushed in the grinding jaws. You don’t even
manage to let lose a gurgling scream through your blood.'),
 (88,'Desert','You flee down the winding, dried up river bed into the valley of the canyon. As you race
around the corner, you notice that the valley’s walls narrow to about three of your
shoulder’s width. You glimpse behind you to see the wurm has had to abandon the
sand to pursue you over the rock. Its movements are almost awkward on to surface and it seems to struggle with the narrowing passage. You run another corner only to be
faced with a sheer wall of rock. You can imagine the waterfall that would have been
formed from such a drop had the liquid been present. Beneath the wall, you can see a
shallow, smooth hole about a foot deep where the liquid would have hit. You are forced
to stop and face the creature.'),
 (89,'Desert','A screech pieces the air, causing you to cover your ears in reflex. The wurm writhes,
bashing its head on the valley walls. With each hit, you hear a dull cracking sound as if
the creature’s skull is breaking open. With a final, violent spasm, the wurm collapses in
the entrance to the valley. Unsure if the creature is truly dead, you begin to stab into the gaping jaw over and over again until you break out in a sweat. You collapse to the
ground as you catch your breath. When you finally stand again, you notice that
somehow, during the wurm’s death struggles, a bit of its spine has broken through the
skin. It looks almost like barbed wire. You decide to yank out the spine. The top part of
the wurm’s jaw snaps free and roughly 5 feet of the spine comes free before snapping
away from the rest. You find if you hold the jaw bone, the spine is limber enough to act
as a barbed whip.
'),
 (90,'Desert','You notice that although your heartbeat has slowed, you are leg feels as though a
heating pad is on it under the spot where the blood has stained your suit. You recall the
chill you felt from the north that turned you away and you turn to the hole now filled with
the wurm’s blood. You fill the shell with the blood then climb over the corpse to return to
your engine part.
'),
 (91,'Desert','When the player returns to the spaceship, they will apply the blood to the suit before heading out to the glacier zone'),
 (92,NULL,NULL),
 (93,NULL,NULL),
 (94,NULL,NULL),
 (95,NULL,NULL),
 (96,NULL,NULL),
 (97,NULL,NULL),
 (98,NULL,NULL),
 (99,NULL,NULL),
 (100,NULL,NULL),
 (101,NULL,NULL),
 (102,NULL,NULL),
 (103,NULL,NULL),
 (104,NULL,NULL),
 (105,NULL,NULL);
CREATE TABLE `Location` (
	`location_id`	INTEGER,
	PRIMARY KEY(`location_id`)
);
CREATE TABLE `Invetory_Ship_Part` (
	`inventory_id`	INTEGER,
	`ship_part_id`	INTEGER,
	FOREIGN KEY(`inventory_id`) REFERENCES Inventory,
	FOREIGN KEY(`ship_part_id`) REFERENCES Ship_part
);
CREATE TABLE `Inventory_Weapon` (
	`inventory_id`	INTEGER,
	`weapon_id`	INTEGER,
	FOREIGN KEY(`inventory_id`) REFERENCES Inventory,
	FOREIGN KEY(`weapon_id`) REFERENCES Weapon
);
CREATE TABLE "Inventory_Suit_Part" (
	`suit_part_id`	INTEGER,
	`inventory_id`	INTEGER,
	FOREIGN KEY(`suit_part_id`) REFERENCES `Suit_Part`,
	FOREIGN KEY(`inventory_id`) REFERENCES `Inventory`
);
CREATE TABLE "Inventory" (
	`Invetory_id`	INTEGER,
	`entity_id`	INTEGER,
	`player_id`	INTEGER,
	PRIMARY KEY(`Invetory_id`),
	FOREIGN KEY(`entity_id`) REFERENCES Entity,
	FOREIGN KEY(`player_id`) REFERENCES Player
);
CREATE TABLE `Exit-Room` (
	`room_id`	INTEGER,
	`exit_id`	INTEGER,
	FOREIGN KEY(`room_id`) REFERENCES Room,
	FOREIGN KEY(`exit_id`) REFERENCES Exit
);
CREATE TABLE `Exit` (
	`exit`	INTEGER,
	PRIMARY KEY(`exit`)
);
CREATE TABLE `Entity` (
	`Entity_id`	INTEGER,
	PRIMARY KEY(`Entity_id`)
);
COMMIT;
