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
package net.unicon.kaltura.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import net.unicon.kaltura.KalturaMediaService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sakaiproject.nakamura.api.files.FilesConstants;


/**
 * Unit testing for the kaltura service
 * 
 * @author Aaron Zeckoski (azeckoski @ vt.edu)
 */
public class KalturaServiceTest {

    private KalturaMediaService service;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = new KalturaMediaService();
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
        Map<String, Object> contentProperties = new HashMap<String, Object>();
        contentProperties.put(FilesConstants.POOLED_CONTENT_FILENAME, "AZ-title");
        title = service.makeKalturaTitle(contentProperties, 0);
        assertNotNull(title);
        assertEquals("AZ-title - 1", title);

        contentProperties.clear();
        title = service.makeKalturaTitle(contentProperties, 0);
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

}
