<h1>Nearest Neighbour Classifier using VP Tree</h1>

<p align="center">
<img src="https://img.shields.io/badge/made%20by%20-matakshay-blue">
<img src="https://img.shields.io/badge/java-11.0.5-orange">
<img src="https://img.shields.io/badge/contributions-welcome-brightgreen">
<img src="https://badges.frapsoft.com/os/v1/open-source.svg?v=103">
</p>

<p align="justify">
This is an efficient Nearest Neighbour Classifier for classifying images of handwritten digit from the MNIST dataset. It uses a VP Tree to pre-process the images, thus reducing query time complexity. This project was done as part of the Data Structures & Algorithms course at IIT Delhi.
</p>

<h3> TABLE OF CONTENTS </h3>
<ol type="I">
    <li><a href="#intro"> Introduction </a></li>
    <li><a href="#algo"> k-NN Algorithm </a></li>
    <li><a href="#VPTree"> VP Tree </a></li>
    <li><a href="#dataset"> Dataset </a></li>
    <li><a href="#usage"> Usage </a></li>
    <li><a href="#acknowledgement"> Acknowledgement </a></li>
</ol>

<h2 id="intro">Introduction</h2>
<p align="center">
<figure>
    <img src="mnist.png",
         alt="MNIST Classification",
         width=1000,
         height=400>
</figure>
</p>
<p align="justify">
    Classification is a fundamental task in Machine Learning. Given a labelled dataset of points and their classes, classification essentially involved using this dataset to identify the class for each query point. Classification tasks, in general can involve more than two classes as well (multi-class classification). This project, for example involves 10 classes (digits 0-9).
</p>

<h2 id="algo">k-Nearest Neighbours Algorithm</h2>
<p align="justify">
    The k-NN Algorithm is one of the classic base-line algorithms in Machine Learning and Pattern Recognition. Due to its simple structure and implementation, it is often the first approach adopted in most classification problems, before more sophisticated techniques are considered.
    <br>
    The traditional algorithm has a test time complexity which is linear in the size of the train dataset (assuming k is much smaller than the size of training set) for each query. For large dataset, this approach can become computationally much expensive. Here we consider an efficient k-NN algorithm which uses a VP Tree data structure to pre-process and store the train data in such a manner, so that during test time, the nearest neighbour for a query can be obtained much more quickly. With this optimised approach, test time complexity per query can be improved to become logarithmic in the size of train dataset.
</p>

<h2 id="VPTree"> VP Tree </h2>
<p align="justify">
    Vantage-Point tree (or VP Tree) is an example of a metric tree. Metric trees are useful for storing data points defined in a metric space. At each level, a VP Tree divides the data points into two sub-parts, according to their similarity (or distance) from a chosen vantage point. Points where are closer to the vantage point than a threshold are store in the left subtree, while the remaining data points are stored in the right subtree. In this way, the entire dataset is stored in the tree by successively diving it into two halves at each node. The leaf nodes essentially contain a single data point.
    <br>
    While searching for the nearest neighbour of a query point, the recursive process starts from the root node. At each level, based of the values of threshold distance (for that node), distance of query point from vantage point (of that node) and the distances of the points encountered till that instant, the algorithm decides in which of the sub-trees to enter. This recursive techniques greatly reduces the number of distance comparisons needed and in turn improved the query time complexity.
    <br>
    A key hyperparameter of choice here, is the distance metric to be used for comparing the similarity between two data points. Here I experimented with three different metric and obtained the following results-
    <ul>
        <li> Using <b>Manhattan distance</b> as the metric gave an accuracy of <i>96.31%</i> </li>
        <li> <b>Euclidean distance</b> gave the highest accuracy of <i>96.91%</i></li>
        <li> Using <b>Chebyshev distance</b> metric gave an accuracy of <i>79.62%</i></li>
    </ul>
</p>

<h2 id="dataset"> Dataset </h2>
<p align="justify">
    The MNIST dataset <a href="http://yann.lecun.com/exdb/mnist/">(http://yann.lecun.com/exdb/mnist/)</a> has been a landmark dataset in Machine Learning and Pattern Recognition. It consists of more than 70,000 grayscale images of handwritten numeric digit, divided into a train set (of 60,000 images) and a test dataset (10,000 images). Each image has a fixed dimension of 28x28 pixels. Each image comes labelled with one of the 10 possible classes (0-9).
    Over the years this dataset has been used for testing many Convolutional Neural Networks and algorithms Machine Learning and Computer Vision.
</p>

<h2 id="usage">Usage</h2>
<ol>
    <li>
        Clone the repository to your system and head over to it <br>
        <code> git clone https://github.com/matakshay/NN-Classifier-using-VPTree</code> <br>
        <code> cd NN-Classifier-using-VPTree </code>
    </li>
    <li>
        Before moving to the next step, ensure that JDK version 11.0.5 has been installed on the system
    </li>
    <li>
        Compile the project <br>
        <code> javac -Xlint:unchecked NNClassifier/Main.java </code>
    </li>
    <li>
        Execute the code with the following command. This will read the dataset, build the classifier & construct a VP Tree (using the 60,000 images from train set), use the test set images to obtain the predictions and lastly will compute the accuracy of the classifier over the test set.
        <br>
        <code> java NNClassifier/Main </code>
        <br>
        By default it uses the l2 metric (Euclidean distance) for computing the similarity between two images. One can pass "l1", "linf" as a command-line argument (while executing the code) to set the metric to l1 (Manhattan distance) or linf (Chebyshev distance) respectively.
    </li>
</ol>

<h2 id="acknowledgement">Acknowledgement</h2>
<p align="justify">
I referred the following research papers, articles and course lectures while working on this project-
</p>
<ul>
    <li> http://web.cs.iastate.edu/~honavar/nndatastructures.pdf </li>
    <li> http://stevehanov.ca/blog/?id=130 </li>
    <li> https://en.wikipedia.org/wiki/Vantage-point_tree </li>
    <li> https://www.cs.toronto.edu/~urtasun/courses/CSC411_Fall16/05_nn.pdf </li>
    <li> https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm </li>
    <li> https://courses.cs.washington.edu/courses/cse373/02au/lectures/lecture22l.pdf </li>
    <li> http://vlm1.uta.edu/~athitsos/nearest_neighbors/ </li>
</ul>
