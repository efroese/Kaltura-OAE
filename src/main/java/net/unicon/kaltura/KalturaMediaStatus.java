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

import org.sakaiproject.nakamura.api.media.MediaStatus;

import com.kaltura.client.enums.KalturaEntryStatus;

/**
 * Convert Kaltura media statuses to the simpler OAE statuses.
 *
 * @author Erik Froese erik@hallwaytech.com
 */
public class KalturaMediaStatus implements MediaStatus {

  private KalturaEntryStatus kalturaStatus;

  public KalturaMediaStatus() { }

  public KalturaMediaStatus(KalturaEntryStatus kalturaStatus) {
    super();
    this.kalturaStatus = kalturaStatus;
  }

  @Override
  public boolean isReady() {
    return kalturaStatus != null && kalturaStatus == KalturaEntryStatus.READY;
  }

  @Override
  public boolean isProcessing() {
    return kalturaStatus != null && 
        (kalturaStatus == KalturaEntryStatus.PENDING
        || kalturaStatus == KalturaEntryStatus.PRECONVERT);
  }

  @Override
  public boolean isError() {
    return kalturaStatus != null && 
        (kalturaStatus == KalturaEntryStatus.BLOCKED
        || kalturaStatus == KalturaEntryStatus.ERROR_CONVERTING
        || kalturaStatus == KalturaEntryStatus.ERROR_IMPORTING
        || kalturaStatus == KalturaEntryStatus.INFECTED);
  }
}
