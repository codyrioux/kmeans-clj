# kmeans-clj

A simple k-means clustering implementation in Clojure. I needed clustering to find
"concepts" in a clojure NLP library I'm working on and figured this component is
reusable enough.

As a note, because clusters are defined as the average of their elements if a
cluster ends up empty I let it stay that way. Actually it gets removed and added
as an empty cluster on termination.

## Usage

See `sample.clj` for sample usage.
Inputs to `k-means` are:

- A seq of elements to cluster
- A vector distance meansure
- A vectorizer for elements
- The number of clusters desired
- The maximum number of iterations

## License

Copyright © 2014 Cody Rioux

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
