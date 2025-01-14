# Weather App

This is a simple weather app that fetches and displays the latest weather data for a user's location from a list of coordinates. The location updates every 10 seconds, and the app loops back to the first location once all coordinates have been cycled through.

## Features

- Displays the current weather for the user's location based on a list of provided coordinates.
- Updates location every 10 seconds, looping back to the first location after reaching the end.
- Weather data is fetched from the Open-meteo API, which provides free access to current weather data.

## Coordinates

The following list of coordinates represents the user’s location timeline:

- 53.619653, 10.079969
- 53.080917, 8.847533
- 52.378385, 9.794862
- 52.496385, 13.444041
- 53.866865, 10.739542
- 54.304540, 10.152741
- 54.797277, 9.491039
- 52.426412, 10.821392
- 53.542788, 8.613462
- 53.141598, 8.242565

## API Integration

The app uses the [Open-meteo.com](https://open-meteo.com) weather API to fetch weather data based on latitude and longitude.

### Example Request
To fetch the weather for a specific location, you can use the following API endpoint:
https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&current_weather=true


## Assumptions

- If the user exits the app or an error occurs, the app will restart from the first coordinate.
- The app will display basic weather information and can be extended to include more details.

## Improvements

- Add offline support to show previously fetched weather data if there is no internet connection.
- Show more detailed weather information (e.g., humidity, wind speed, etc.).
- Allow the user to see weather details for upcoming days.
- Add animations for location transitions.
- Provide options to switch between different units (e.g., Celsius to Fahrenheit, KM/H to m/s).
- Optimize images for different screen densities by converting PNG files to SVG or resizing them.
- Show the current temperature for the rest of the day starting from the current hour.

## Screenshots

Below are some screenshots of the Weather App:

![Weather Screen](screenshot/weather_screen.png)


## Contributing

Feel free to fork the project and submit issues or pull requests.

