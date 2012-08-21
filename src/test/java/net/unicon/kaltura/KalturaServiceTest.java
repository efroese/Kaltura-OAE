/**
 * Copyright 2011 Unicon (R) Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package net.unicon.kaltura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.unicon.kaltura.KalturaMediaService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sakaiproject.nakamura.api.lite.Repository;
import org.sakaiproject.nakamura.api.lite.Session;
import org.sakaiproject.nakamura.api.lite.StorageClientException;
import org.sakaiproject.nakamura.api.lite.accesscontrol.AccessDeniedException;
import org.sakaiproject.nakamura.api.lite.authorizable.AuthorizableManager;
import org.sakaiproject.nakamura.api.lite.content.Content;
import org.sakaiproject.nakamura.api.lite.content.ContentManager;
import org.sakaiproject.nakamura.lite.BaseMemoryRepository;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;


/**
 * Unit testing for the kaltura service
 * 
 * @author Aaron Zeckoski (azeckoski @ vt.edu)
 */
public class KalturaServiceTest {

    private KalturaMediaService service;

    private ContentManager cm;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = new KalturaMediaService();
        service.audioExtensions = ImmutableSet.of(".mp3");
        service.videoExtensions = ImmutableSet.of(".mp4", ".mov");
        BaseMemoryRepository baseMemoryRepository = new BaseMemoryRepository();
        Session session = baseMemoryRepository.getRepository().loginAdministrative();
        cm = session.getContentManager();
        cm.update(new Content("mov1", ImmutableMap.of("mimeType", (Object)"video/quicktime")));
        cm.saveVersion("mov1", ImmutableMap.of("mimeType", (Object)"video/quicktime", "onlyinthisversion", "only1"));
        cm.saveVersion("mov1", ImmutableMap.of("mimeType", (Object)"video/quicktime", "onlyinthisversion", "only2"));
        service.repository = (Repository)baseMemoryRepository.getRepository();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        service = null;
    }

    @Test
    public void testIsFileVideo() {
        assertTrue( service.acceptsFileType("", ".mp4"));
        assertTrue( service.acceptsFileType("video/mpeg", ""));
        assertTrue( service.acceptsFileType("video/quicktime", ".mov") );

        assertFalse( service.acceptsFileType("", ".xls") );
        assertFalse( service.acceptsFileType("text/html", "") );
        assertFalse( service.acceptsFileType("text/plain", ".txt") );
        assertFalse( service.acceptsFileType("", "") );
        assertFalse( service.acceptsFileType(null, null) );

        // NOTE: cannot throw exception
    }

    @Test
    public void testIsFileAudio() {
        assertTrue( service.acceptsFileType("", ".mp3") );
        assertTrue( service.acceptsFileType("audio/mpeg", "") );
        assertTrue( service.acceptsFileType("audio/x-aiff", ".aif") );

        assertFalse( service.acceptsFileType("", ".xls") );
        assertFalse( service.acceptsFileType("text/html", "") );
        assertFalse( service.acceptsFileType("text/plain", ".txt") );
        assertFalse( service.acceptsFileType("", "") );
        assertFalse( service.acceptsFileType(null, null) );

        // NOTE: cannot throw exception
    }

    @Test
    public void testMakeKalturaTitle() {
        String title = null;
        title = service.makeKalturaTitle("AZ-title", 0);
        assertNotNull(title);
        assertEquals("AZ-title - 1", title);

        title = service.makeKalturaTitle("", 0);
        assertNotNull(title);
        assertEquals("title - 1", title);

        title = service.makeKalturaTitle(null, 0);
        assertNotNull(title);
        assertEquals("title - 1", title);
    }

    @Test
    public void testMakeKalturaTags() {
        String tags = null;
        tags = service.makeKalturaTags(new String[] {"az","bz"});
        assertNotNull(tags);
        assertEquals("az,bz", tags);

        tags = service.makeKalturaTags(new String[]{ });
        assertNotNull(tags);
        assertEquals("", tags);
    }

    @Test
    public void testGetOrderedContentVersionNumber() throws AccessDeniedException, StorageClientException {
        List<String> versionIds = cm.getVersionHistory("mov1");
        assertEquals(2, versionIds.size());
        assertEquals(2, service.getOrderedContentVersionNumber("mov1", versionIds.get(0)));
        assertEquals(1, service.getOrderedContentVersionNumber("mov1", versionIds.get(1)));
        assertEquals(1, service.getOrderedContentVersionNumber("mov1", null));
        assertEquals(1, service.getOrderedContentVersionNumber("mov1", "INVALID_VERSION"));
    }

}
