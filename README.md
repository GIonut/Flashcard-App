# BachelorThesis

### Sneak peek over my thesis presentation:

Since the emergence of WEB2.0 there has been a growth in E-learning System development.
Those systems have become more and more accessible to users, phenomenon that gave researchers the opportunities as well as the necessary data material to study the behaviour of such systems.
Following their discoveries, I have compiled a list of challenges to which E-learning Systems that have proved to be efficient and effective in reaching their goal
answer.

These challenges were further processed into base-line features that an Aynchronous E-learning System should provide to their users. The Base-line features can be described as following: Multimedia, Easiness of Access, Practicing Mechanism.

As I was saying, since the emergence of WEB2.0 the number of newly developed E-learning Systems surged. However among this cluster of E-learning Devices that have been developed in the last 20 years, only a small part is actually respecting the base-line mentioned above.

Furthermore, considering the list of base-line features stated earlier, I have documented a list of modern technologies that can facilitate the process of developing client-server systems for educational purposes, that are also respecting the demands formerly expressed.

These technologies are best fitted for Asynchronous Practice Based E-learning Systems, with the 
following usecases.

The most important usecase is allowing learners to practice. They can practice Cards which 
are organized in Decks. Decks are organized in Portals relative to the field of study.
As the Multimedia base-line feature is stating, the learning process should be facilitated by
the use of images, sound or rich text. One top technology that responds to the Multimedia feature
is AWS Serverless Image Handler.

Its purpose is to provide a better alternative to storing blobs for images in your relational database. It is cost efficient, secure, and it provides further conveniences for image preprocessing with Thumbor filters, smart cropping to detect faces, or content moderation with Amazon Rekognition, an important feature in E-learning systems wich target a young audience. AWS CloudFront distribution comes with a caching layer in order to reduce cost. However, cost can be further minimized by using a local storage cache on the client side, that acts as a proxy for the aws service, intercepting the request and serve the user from the cache storage, without unecessary hitting the s3 bucket, or the cloud front. The cache storage is instantiated by the means of a parametrized factory method, for the two storage types: local storage and in Memory.

In order for the user to access multimedia content, teachers shuld be able to create cards with associated multimedia elements. Serverless image handler also responds to this usecase because it can be configured to allow PUTObject Requests to populate the s3 bucket.

Turning back to the Practicing Use case, It is important that the user can practice anywhere. This Challenge is mapped to the Easiness of Access base-line feature and it is most commonly fulfilled by mobile application. However, investing in applications that are native to each mobile platform could be costly. To this challenge, a good response can constitute Ionic and Capacitor pair of tools.

Further, another important part of the practicing use case is the feature of allowing the learner to track its progress, which comes with investing time in practice session. A tool that can help with tracking the progress is Ebisu Intelligent Quiz Scheduling Model. It is a model founded on Bayesian statistics and exponential forgetting, and can be used to provide an answer to the following question:

How does the student's performance on a review change that fact's future review schedule?

Where the progress is tracked in terms of how long the student can retrieve the information he learned before he forgets it. The longer this time period in which he is able to recall the information is, the better the progress.

The way it works is by storing the data model for each fact in the quiz app. The data model is a three tuple (alpha, beta, t), where alpha and beta defines the 
prior Beta distribution for recalling the fact t time units after it's most recent review. For this, the application must store the data model together with a timestamp of the last review. Each time the fact is reviewed again, a new model is computed from the previous model alpha and beta,
and the difference between the stored timestamp and the current time. Ebisu is providing an API function for this feature
which is updateRecall. To actually show the progress to the user, one can fetch the current and previous models
and apply the API function predictRecall, for a given time-frame. this way, the user can see the way the ability of recalling the fact has changed between practice sessions.

Even if it is not a base-line feature of an E-learning system from the point of view of psychologists, from the point of view of application development, it is, without doubts, fundamental. One common and necessary use case is authentication. The user should be able to authenticate himself to the application. To authenticate he must first register and activate his account, by accessing the link sent by e-mail. Security by means of authorizations, can be achieved with the Spring boot framework. Alongside spring security mechanism, JWT is used to secure the login and the authorities such that they can be transferred between the server and the client without them being altered in any way by a user. In this way, certain roles can be attributed to certain users and limit 
their interaction with the application based on their roles. For example, allowing access to certain endpoints or rendering certain elements on the screen.
