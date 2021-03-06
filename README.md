
This is the Sakai OAE / Kaltura integration. 
Developed by Aaron Zeckoski (Lead) of Unicon for Kaltura and New York University.
Modified to support the OAE media streaming and transcoding bundle by Erik Froese for rSmart.

Unicon:       http://www.unicon.net/
rSmart:       http://www.rsmart.com/
Hallway Tech: http://www.hallwaytech.com/

Sakai OAE:  http://sakaiproject.org/node/2239
Kaltura:    http://corp.kaltura.com/

Introduction
------------
This integration will intercept media files which are loaded into Sakai OAE and then 
send them over to the Kaltura server (as configured). The OAE content item will be updated
with Kaltura specific data so that a Kaltura player can be rendered for the user.
This is all seamless to the end user (they should not even realize they are using Kaltura).

The original bundle sent video to Kaltura after it had been stored by OAE. The content
stored on OAE was never used or deleted, so it was wasting space. The media bundle,
developed by Carl Hall and Mark Triggs in conjunction with Hallway Technologies,
rSmart, and UC Berkley, avoids storing media file in the OAE content store. It instead
stores uploaded media in a temporary area, uploads it to the remote store, and deletes it.


Source
------
The source code is located on https://github.com at:

	https://github.com/Unicon/Kaltura-OAE

Checkout the source code using git version 1.7 (or higher)

    git clone git://github.com/Unicon/Kaltura-OAE.git kaltura

As of the time of this writing, the media bundle has not yet been merged into the
OAE community repository. You can fetch it from Carl Hall's branch.

https://github.com/thecarlhall/nakamura/tree/ucbvideo


Build
-----

From a nakamura clone

	git remote add thecarlhall https://github.com/thecarlhall/nakamura.git
	git fetch thecarlhall
	git checkout -b ucbvideo thecarlhall/ucbvideo
	mvn clean install -f contrib/media/common/pom.xml

From the base directory of the code (where this README is located)

	mvn clean install

NOTE: Maven 2.2.x and Java 1.6 are required to build the source


Deploy
------

Upload the contrib/media/common/target/org.sakaiproject.nakamura.media-*.jar into your OAE instance using the system console (your.server/system/console/bundles)
OR
deploy into a running OAE instance using Maven:
	mvn clean install -f contrib/media/common/pom.xml -Predeploy


Upload the target/net.unicon.kaltura-*.jar into your OAE instance using the system console (your.server/system/console/bundles)
OR 
deploy into a running OAE instance using Maven:

	mvn clean install -Predeploy


Deploy in the app jar
---------------------

Follow the build instructions above.

Add the following to nakamura/app/src/main/bundles/list.

        <bundle>
                <groupId>org.sakaiproject.nakamura</groupId>
                <artifactId>org.sakaiproject.nakamura.media.common</artifactId>
                <version>1.5.0-SNAPSHOT</version>
        </bundle>
   	<bundle>
                <groupId>org.sakaiproject.nakamura</groupId>
                <artifactId>net.unicon.kaltura</artifactId>
                <version>0.3-SNAPSHOT</version>
        </bundle>

Configuration
-------------
Use the system console (your.server/system/console/configMgr) and click on "Kaltura Service" and edit the fields
OR 
create a load/net.unicon.kaltura.service.KalturaService.cfg file
(the load directory should be in the same dir as your OAE installation, you may need to create this dir) 
with at least the following settings in it (see system/console for the complete set):

    kaltura.partnerid=111
    kaltura.secret=sssssssssssssssss
    kaltura.adminsecret=aaaaaaaaaaaaaaaaaaaa


UI Configuration
----------------
See OAE config docs:
https://confluence.sakaiproject.org/display/3AK/OAE+Configuration+and+Deployment

To be set in config_custom.js (usually in 3akai-ux/dev/configuration/config_custom.js):

    config.kaltura = {
        enabled: true,                                   // true / false
        serverURL:  "http://kaltura.com",                // location of the Kaltura server
        partnerId:  111,                                 // assigned partner ID
        playerId: 222222                                 // ID of player you'd like to use
    };


Send any questions about the media-framework-enabled port (this repository) to Erik Froese (erik@hallwaytech.com)

Send any questions about the original bundle Charise M. Arrowood (carrowood@unicon.net).

* Aaron Zeckoski (azeckoski @ unicon.net) (azeckoski @ vt.edu)

* Erik Froese (erik@hallwaytech.com)
