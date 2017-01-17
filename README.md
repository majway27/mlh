# mlh
Mother's Little Helper 2, Android App

## ToDo
* ~~Simple GUI~~
  * ~~Schedule request now~~
  * ~~Schedule pending request~~
  * ~~View pending requests~~
* Simple post
  * ~~Basic static values~~
  * ~~AsyncTask for call~~
  * ~~Simple GUI Values for post~~
  * Spinner array key/value for gui values post
  * Validate mechanize form management
* Save pending request, scheduler picks up and posts
  * Alarmmanager, Jobscheduler eval
  * Scheduler understands 2 day wait and monitors and schedules valid targets from list
* End user one-time setup, shared prefs ?
  * User friendly name
  * instance
  * stored creds
  * customer_id
  * location_id
* Apt request
  * Methods
    * Svc login via stored creds
    * Pick Time Frame (973 - 30 minutes, 974 - 1  hour, 975 - 1 hour 30 minutes
    * Pick Kid Type (782 - Child, 783 - Infant)
    * Action - viewappts
    * Action - confirm
    * Action - confirm, finalize_appt
  * Use GUI values
    * first_appt_time, last_appt_time
    * service_id  // Time Frame
    * e_id // Kid type
    * child1, child2, etc
    * selected_children, child_count // Pipe delinated
    * children_list // Comma delinated
    * Scheduling Related // appt_start_time:
      appt_end_time:
      date_ymd:
      starting_date:
      next_date:
      prev_date:
      next_date1:
      prev_date1
  * Use setup values
  * Use stored creds
  * Cleanup GUI stuff in xml

## Considering
* Push notifications when activity happens?
* Direct link to site for manual verification?
* MPL ?
* Fancy GUI, Pretty Icons ?
  * "New Apt" should be google style plus button in lower right.
  * View Scheduled should be a TextView panel in main page
  * Should say welcome/hello user on main page

## Notes
* Requires API 21, min version 5
* Android Studio

## Mechanize
* http://stackoverflow.com/questions/16608135/android-studio-add-jar-as-library
* https://github.com/GistLabs/mechanize
* http://search.maven.org/#artifactdetails%7Ccom.gistlabs%7Cmechanize%7C2.0.0-RC1%7Cjar


