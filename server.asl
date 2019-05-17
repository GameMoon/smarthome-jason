// Agent server in project smarthome.mas2j



/* Initial beliefs and rules */

highTempNo(0).
highSmokeNo(0).

/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello server.").


+highSmoke[source(WHERE)] <- +highSmoke(WHERE);
							 -highSmoke[source(WHERE)];
							 ?highSmokeNo(S);
							 -highSmokeNo(S);
							 +highSmokeNo(S+1);
							 .send(owner,tell,highTemp(S+1));
							 ?highTempNo(T);
							 .send(owner,tell,highSmoke(T));
							 .print("High smoke at ",WHERE).
							 
+lowSmoke[source(WHERE)] <- -highSmoke(WHERE);
							-lowSmoke[source(WHERE)];
							?highSmokeNo(S);
							 -highSmokeNo(S);
							 +highSmokeNo(S-1);
							 .send(owner,tell,highTemp(S-1));
							 ?highTempNo(T);
							 .send(owner,tell,highSmoke(T));
							.print("Smoke is not high at ",WHERE).
							
+highTemp[source(WHERE)] <- +highTemp(WHERE);
							 -highTemp[source(WHERE)];
							 ?highTempNo(T);
							 -highTempNo(T);
							 +highTempNo(T+1);
							 .send(owner,tell,highTemp(T+1));
							 ?highSmokeNo(S);
							 .send(owner,tell,highSmoke(S));
							 .print("High temp at ",WHERE).
							 
+lowTemp[source(WHERE)] <- -highTemp(WHERE);
							-lowTemp[source(WHERE)];
							?highTempNo(T);
							 -highTempNo(T);
							 +highTempNo(T-1);
							 .send(owner,tell,highTemp(T-1));
							 ?highSmokeNo(S);
							 .send(owner,tell,highSmoke(S));
							.print("Temp is not high at ",WHERE).
							
+fireAt[source(WHERE)] <- .send(owner,tell,run);
						  .send(guest,tell,run).
						  
+highTempNo(A):A>1 <- .send([kitchen,hall,bedroom,livingroom,bathroom],tell,extinguish);
				  .send([owner,guest],tell,run).
+highSmokeNo(A):A>2 <- .send([kitchen,hall,bedroom,livingroom,bathroom],tell,extinguish);
				  .send([owner,guest],tell,run).
				  
+highTempNo(A):A<2 <- .send([kitchen,hall,bedroom,livingroom,bathroom],tell,extinguishOff);
				  .send([owner,guest],tell,stoprun).
+highSmokeNo(A):A<3 <- .send([kitchen,hall,bedroom,livingroom,bathroom],tell,extinguishOff);
				  .send([owner,guest],tell,stoprun).
				  
