# soccerMatchStatsAnalyser
Consider the following simplified live stats analyser system for World Cup 2018. The system will be able to
display the stats information with a given time stamp based on the events received during a soccer match.
• An event record is comprised of the following elements:
o Time: Game starts from 00:00, break at 45:00, ending at 90:00.
o Event Type:
§ START: Game starts with Team A (or B) taking kick-off
§ POSSESS: Team A (or B) takes possession
§ SHOT: Team A (or B) shoots
§ SCORE: Team A (or B) scores
§ BREAK: Half-time break
§ END: Game ends
o Team: The team applied to the event
• The kick-off to start the second half is taken by the other team.
• To calculate the total possession time for Team X, add up all the individual time periods that team X
has possession. Each possession time is calculated either from when the game starts with Team X or
Team X takes possession, and then ends when game ends or the other team takes possession.
• Event records are received in correct time order.
• The input file is well formed and is not missing data.
