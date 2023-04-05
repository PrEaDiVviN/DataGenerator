
# Homework - Sisteme bazate pe evenimente

## Evaluation

The paralelization has been made using threads, with a variable factor of paralelism.

### Evaluation results

#### Computer specs
- Processor	Intel(R) Core(TM) i7-7500U CPU @ 2.70GHz, 2901 Mhz, 2 Core(s), 4 Logical Processor(s)
- System Type	x64-based PC
- Installed Physical Memory (RAM)	16.0 GB



For 10 000 Publications:
- 1 thread (in ms): 1922, 1645, 1392, 1646, 1559
  - min: 1392
  - max: 1922
- 4 thread (in ms): 2064, 1244, 1898, 1614, 1508
  - min: 1244
  - max: 2064

For 10 000 Subscriptions with 50% probability for every property:
- 1 thread (in ms): 1402, 1459, 1580, 1262, 1169
  - min: 1169
  - max: 1580
- 4 threads (in ms): 1100, 989, 911, 1081, 1126
  - min: 911
  - max: 1126

## Running

For running the program, you have to change in the Main.java file the following

### Generation of publications data

Uncomment lines 
```
PublicationDataManager manager = new PublicationDataManager(PUBLICATIONS_PROPERTIES_FILEPATH, 
    PUBLICATIONS_OUTPUT, 4, 100);
manager.startWork();
```
And comment the rest.

For editing the publications parameters, you have to go to `src/main/resources/publications.properties` and change the values for the existing parameters.


### Generation of subscriptions data

Uncomment lines 
```
SubscriptionDataManager manager = new SubscriptionDataManager(PUBLICATIONS_PROPERTIES_FILEPATH,
    SUBSCRIPTION_PROPERTIES_FILEPATH, SUBSCRIPTION_OUTPUT, 4, 100);

manager.startWork();
```
And comment the rest.

For editing the subscriptions parameters, you have to go to `src/main/resources/subscriptions.properties` and change the values for the existing parameters.

The values are in the interval `[0, 100]`, with `-1` if said parameter doesn't have a probability.
