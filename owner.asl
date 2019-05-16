// Agent owner in project smarthome.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").
+highTemp(T) <- ?highTemp(B);
				 -highTemp(B).
				 
+highSmoke(T) <- ?highSmoke(B);
				 -highSmoke(B).

+run[source(server)] <- run(owner).

+stoprun[source(server)] <- -run[source(server)];
							-stoprun[source(server)].
