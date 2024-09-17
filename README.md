Ceiling Fan
A simple ceiling fan with 2 pull cords to control speed and direction.

Description
This application models a ceiling fan with the following characteristics:

Two pull cords:
Speed Cord: Each pull increases the speed by one level. The fan has 3 speed settings and an "off" setting (speed 0). If the cord is pulled while on speed 3, the fan returns to the "off" setting.
Direction Cord: Each pull reverses the direction of the fan at the current speed. The direction remains reversed as you cycle through the speed settings until reversed again.
Power Assumption: The fan is always powered on (no wall switch).

Functionality
Increase Speed: The pullSpeedCord() method increases the fan's speed or resets it to "off" if it's currently at maximum speed.
Reverse Direction: The pullDirectionCord() method reverses the fan's rotation direction while maintaining the current speed.
Get Current State: Methods getSpeed() and isClockwiseDirection() return the current speed and rotation direction of the fan.

Requirements
Java 11 or higher
Maven 3.6.0 or higher

Build and Run/Project
mvn clean compile

Run Tests
mvn test

Clone the Repository
git clone https://github.com/Kannan-Lab/CeilingFan.git

Author
Kannan Rajappan
