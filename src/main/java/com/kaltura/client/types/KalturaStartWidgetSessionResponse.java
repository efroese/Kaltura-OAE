package com.kaltura.client.types;

import java.util.IllegalFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectFactory;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Sun, 19 Jun 11 02:46:50 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaStartWidgetSessionResponse extends KalturaObjectBase {
    public int partnerId = Integer.MIN_VALUE;
    public String ks;
    public String userId;

    public KalturaStartWidgetSessionResponse() {
    }

    public KalturaStartWidgetSessionResponse(Element node) throws KalturaApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String txt = aNode.getTextContent();
            String nodeName = aNode.getNodeName();
            if (false) {
                // noop
            } else if (nodeName.equals("partnerId")) {
                try {
                    if (!txt.equals("")) this.partnerId = Integer.parseInt(txt);
                } catch (NumberFormatException nfe) {}
                continue;
            } else if (nodeName.equals("ks")) {
                this.ks = txt;
                continue;
            } else if (nodeName.equals("userId")) {
                this.userId = txt;
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaStartWidgetSessionResponse");
        return kparams;
    }
}
