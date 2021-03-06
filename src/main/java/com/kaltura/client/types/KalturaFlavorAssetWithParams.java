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

public class KalturaFlavorAssetWithParams extends KalturaObjectBase {
    public KalturaFlavorAsset flavorAsset;
    public KalturaFlavorParams flavorParams;
    public String entryId;

    public KalturaFlavorAssetWithParams() {
    }

    public KalturaFlavorAssetWithParams(Element node) throws KalturaApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String txt = aNode.getTextContent();
            String nodeName = aNode.getNodeName();
            if (false) {
                // noop
            } else if (nodeName.equals("flavorAsset")) {
                this.flavorAsset = (KalturaFlavorAsset)KalturaObjectFactory.create((Element)aNode);
                continue;
            } else if (nodeName.equals("flavorParams")) {
                this.flavorParams = (KalturaFlavorParams)KalturaObjectFactory.create((Element)aNode);
                continue;
            } else if (nodeName.equals("entryId")) {
                this.entryId = txt;
                continue;
            } 

        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.setString("objectType", "KalturaFlavorAssetWithParams");
        kparams.addObjectIfNotNull("flavorAsset", this.flavorAsset);
        kparams.addObjectIfNotNull("flavorParams", this.flavorParams);
        kparams.addStringIfNotNull("entryId", this.entryId);
        return kparams;
    }
}

