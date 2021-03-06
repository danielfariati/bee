/*
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is mozilla.org code.
 *
 * The Initial Developer of the Original Code is
 * Bluesoft Consultoria em Informatica Ltda.
 * Portions created by the Initial Developer are Copyright (C) 2011
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */
package br.com.bluesoft.bee.upgrade

import br.com.bluesoft.bee.Bee

public class BeeVersionModule {

    def static getLatestVersion() {
        def latest_url = getLatestVersionURL()
        def url_split = latest_url.split("/")

        def version = url_split[url_split.size() - 1]

        return version
    }

    def static getLatestVersionURL() {
        String url_latest = "https://github.com/bluesoft/bee/releases/latest"
        InputStream is
        URLConnection con

        try {
            con = new URL(url_latest).openConnection()
            con.connect()

            is = con.getInputStream()
            String latest_url = con.getURL()

            return latest_url

        } catch (Exception e) {
            println "fatal: error while downloading"
            e.printStackTrace()

        } finally {
            try {
                is.close()
            } catch (IOException ioe) {
                ioe.printStackTrace()
            }
        }
    }

    def static getLatestDownloadURL() {
        String version = getLatestVersion()

        return 'https://github.com/bluesoft/bee/releases/download/' + version + '/bee-' + version + '.zip'
    }

    def static getCurrentVersion() {
        return Bee.getVersion();
    }
}
