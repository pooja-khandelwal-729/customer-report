
# Generate Customer Reports App

This app consumes the multiline string as the input
and generates various reports as the output


## Requirements
* Java 17
* Git
* Maven
* IDE


## Run Locally

Clone the project

```bash
  git clone https://github.com/pooja-khandelwal-729/customer-report.git
```

Go to the project directory

```bash
  cd customer-report-project
```

Install dependencies and compile

```bash
  mvn clean install
```

Run tests

```bash
  mvn test
```

Run the Main class

```bash
  mvn exec:java

  The program will ask to provide the input data in the console.
```


## Input Format

Multiline string in comma(,) delimited format. Each line contains the data in following order -

```bash
customerId,contractId,geozone,teamcode,projectcode,buildduration
```
Sample input data -

```bash
2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s 

In this data the first line would be interpreted as
2343225         is the customerId
2345            is the contractId
us_east         is the geozone
RedTeam         is the teamcode
ProjectApple    is the projectcode
3445s           is the buildduration
```

## Output Format

It will generate four reports from the given data
* The number of unique customerId for each contractId
* The number of unique customerId for each geozone
* The average buildduration for each geozone
* The list of unique customerId for each geozone

Sample output data -

```bash
The number of unique customerId for each contractId
ContractId: 2345 Unique CustomerId count: 3
ContractId: 2346 Unique CustomerId count: 2

The number of unique customerId for each geoZone
GeoZone: eu_west Unique CustomerId count: 2
GeoZone: us_west Unique CustomerId count: 2
GeoZone: us_east Unique CustomerId count: 1

The average buildduration for each geoZone
GeoZone: eu_west Average build duration: 4222.0s
GeoZone: us_west Average build duration: 2216.0s
GeoZone: us_east Average build duration: 3445.0s

The list of unique customerId for each geoZone
GeoZone: eu_west Unique Customer Id List: [3244132, 3244332]
GeoZone: us_west Unique Customer Id List: [1223456, 1233456]
GeoZone: us_east Unique Customer Id List: [2343225]
```
