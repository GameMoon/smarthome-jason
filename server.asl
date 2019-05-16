// Agent server in project smarthome.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+highSmoke[source(WHERE)] <- +highSmoke(WHERE);
							 -highSmoke[source(WHERE)];
							 .print("High smoke at ",WHERE).
							 
+lowSmoke[source(WHERE)] <- -highSmoke(WHERE);
							-lowSmoke[source(WHERE)];
							.print("Smoke is not high at ",WHERE).
							
+highTemp[source(WHERE)] <- +highTemp(WHERE);
							 -highTemp[source(WHERE)];
							 .print("High temp at ",WHERE).
							 
+lowTemp[source(WHERE)] <- -highTemp(WHERE);
							-lowTemp[source(WHERE)];
							.print("Temp is not high at ",WHERE).
