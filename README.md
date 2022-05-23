# SotanSauna
GUI Sauna Application

Background:
While visiting a sauna in Colorado, I discovered a flaw in the sauna's interface logic. The timer that would turn the heating element off starts as soon 
as the sauna is powered. Therefore, it's a not a guarentee the the actual temperature will reach the setpoint as the timer can time out before then. I challenged
myself to build a simple Sauna interface with the logic that the heating element timer starts when the actual temperature equals the target temperature.

Program:
The program will be a GUI that is built to simulate a basic Sauna interface. The target temperature can be set and a timer(10,20,30seconds) must be chosen. Once
that is chosen, the user will click the power button for which the actual temperature will increase until it is equal to the target temp. Once, the actual temp 
equals the target temp, the chosen timer will start. When the timer expires, the actual temperature will decrease back to 68F (the default temp).
