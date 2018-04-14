# Bellman-Ford-Algorithm
A Bellman Ford algorithm implementation in Java.

## How to use it
You must edit **/input/nodes.txt** and **/input/edges.txt** files.

The first one contains a list of all nodes (one per line) in the graph and the first one is the starting node. Example:
```
a
b
c
d
e
f
```
In this case the graph is composed by **6 nodes** and **E is the initial node**.

The second one contains a list of edges in the graph, but there mustn't be **duplicates**. Example:
```
a,b,7,5
a,c,8,*
b,d,4,-
...
start node, end node, weight start to end, weight end to start / * (the same) / - (NOT connected)
```
The difference is in the last piece of the line which **must be compiled following these rules**:
* A to B weights 7, B to A weight 5;
* A to C weights 8, C to A weights the same (8);
* B to D weights 4, D and B are NOT connected.

## Output
When you run the program it creates an **/output/result.txt** file which cointains the **routing table of the first node in /input/nodes.txt file**. Example:
```
Node	Distance	Predecessor
a	0.0		me
b	7.0		a
c	6.0		e
d	10.0		e
e	8.0		b
f	8.0		c
```
