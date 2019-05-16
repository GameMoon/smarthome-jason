// Agent guest in project smarthome.mas2j



/* Initial beliefs and rules */



/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello world.").

+run[source(server)] <- run(guest).

+stoprun[source(server)] <- -run[source(server)];
							-stoprun[source(server)].
