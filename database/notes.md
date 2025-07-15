# Database learnings

I'll be taking notes while learning more about databases in general and MySQL/PostgreSQL.

## SQL
Structured Query Language is the most common way to interact with various flavors of databases.

### Common SQL commands:

#### SELECT:
SELECT is used to query from the database and can be modified with other keywords. The query returns a result set and that can also be saved for later use using the `CREATE VIEW` keywords.

- **Selecting every field of a table:** 
`SELECT * FROM table_name;`
For example:
`SELECT * FROM films;` will give us the entire table back with all the rows (records) and all the columns (fields).
We can also ask for certain fields to display by specifying their names like `SELECT title FROM films;`

- **Selecting fields of a table with certain conditions using `WHERE`:**
`SELECT fields FROM table_name WHERE condition;`
For example:
If I want to see a list of film titles that were released in the year 2000, I could write a command like this: `SELECT title FROM films WHERE release_year = 2000;`
`WHERE` also supports ranges, so we can get a list of titles of films released between 2000 and 2020 by using this command:
`SELECT title FROM films WHERE release_year >= 2000 AND release_year <= 2020;`
Our usual relational operators are usable here, but the not-equal operator in programming `!=` is replaced by `<>` here.

- `AND, OR` can be used to combine different conditions as shown in the previous command. There is also the `BETWEEN` command that can be used to specify ranges. For example we could also write the previous command like this:
`SELECT title FROM films WHERE release_year BETWEEN 2000 AND 2020;`
We can also pick up string values using the keywords `IN`. So instead of writing `WHERE language = 'Japanese' OR language = 'English`, we can write `WHERE language IN ('Japanese', 'English')`

- **Aggregate functions**:
We can do different types of aggregation on the data for better analysis. A few are listed here:

| **Name** | **Usecase** |
| -------- | ----------- |
| `COUNT()` | This function is used to count the number of occurances and is usually used with the `SELECT` statements. Any kind of data fields can be used. |
| `SUM()` | The `SUM()` function is used to find the sum of some numerical data fields. |
| `AVG()` | Similar to `SUM()`, this function is used to find the average of some numerical data. |
| `MIN()` and `MAX()` | These two functions are used to find the minimum and maximum in a certain numerical field. But they can also be used for other kinds of data. For example, in case of strings, they return the first and last entries if the data is sorted alphabetically. |
| `ROUND()` | This function is used to round decimal numbers up to a certain number of spaces. `ROUND(3.24342, 2)` would yield 3.24 for example. There is also negative rounding which affects the places of numbers. For example, if we are only concerned with the thousands places, we could write `ROUND(123456, -3)` and get 123000. | 

- We can use aliasing with `AS` keyword. This will make our life easier and make the data look better. For example we can average the duration of the films and name the column average_film_duration using this command: `SELECT release_year, AVG(duration) AS average_film_duration FROM films GROUP BY release_year`.

- **Finding unique values:**
We can use the `DISTINCT` keyword to get unique values from the data. Typical use looks like this: `SELECT DISTINCT director FROM films` which will return the unique names of directors of the films present in the dataset.

- **Grouping data:**
We can group data by using the `GROUP BY` keyword. Grouping is handy when we are using aggregate functions which consume a lot of data but produces one output. Grouping matches the field to be grouped with this data. Usage can look like this: `SELECT release_year, COUNT(title) FROM films GROUP BY release_year;` which will show each release year and the number of films released in that particular year.

- **Sorting data:**
Sorting data can give good insights and thus is imperative to use. It is done by the `ORDER BY` keyword. We can sort by as many columns as we like and in whatever order we like. For example: if we want to sort the films dataset in such a way that it will get sorted by the latest year first and for a common year, ascendingly by title, we can write our command like this: `SELECT release_year, title FROM films ORDER BY release_year DESC, title;`

- **String matching**
We can match strings with `LIKE, NOT LIKE` with `WHERE` and using the % and _ symbols. For example, if we want to see all the titles beginning with 'A' then we can write `WHERE title LIKE 'A%'`. If we want specific positions like titles which have 't' as the second letter, we can write `WHERE title LIKE '_t%'`. The _ and % symbols can be anywhere in the pattern.

-**Arithmetic**
We can use regular arithmetic with SQL too. `SELECT (5 + 7) AS result;` would give us 12 as the output.


#### JOINs:
We can join two tables together in a number of ways for our convenience. 

`INNER JOIN` helps us connect to databases on a key that is common to both the tables. For example, if a countries table and a capital cities table have common country codes in themselves, we can join them together on the basis of these codes. If we want to pick the name of the country and name of the capital city, the full command would look like this:

`SELECT cities.name, countries.name
FROM cities
INNER JOIN countries
ON cities.country_code = countries.code;`

Note that we can use aliases to refer to tables to make our commands easier and shorter. These aliases can be used for `SELECT`ing and joining `ON` some field.

`FROM countries AS c
INNER JOIN economies AS e`

There is also a shorthand `USING` keyword that can be used only if the columns for joining the two tables have the same name. In that case it may replace the `ON` keyword.

Relational databases will have relationships in between them, the three most common ways are:

- One to One: A person and their fingerprint
- One to Many: An author and their books
- Many to Many: Languages and Countries

There are OUTER JOINs as well. The most common ones are:

- Left join: retains all the information of the left table and populates missing data for the right table with null.
- Right join: retains all the information of the right table and populates missing data for the left table with null.

Right joins are less common because left join is more intuitive and all right joins can be written as left joins as well.

Apart from that there is FULL JOIN which combines the characteristics of both LEFT and RIGHT joins. I also learned about SELF join (join with the same table) and CROSS join which produces every combination of two tables (or columns). The syntax is the same as previous JOINs, with their appropriate keywords.
