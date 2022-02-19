import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from './../authentication.service';
import { PublicationsService } from './../publications.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  events:any;
  auteur:any;
  url:any;
  currentusername:any;
  idevent:any;
  constructor(private pubService:PublicationsService,private secService:AuthenticationService,
    private router:Router,private auth:AuthenticationService) { }

  ngOnInit(): void {
    this.currentusername=this.auth.currentUsername;
    this.pubService.getAllEvents().subscribe(data=>{
      this.events=data;
      console.log(this.events);
      this.auteur="http://localhost:8087/enseignants/";
    })
  }
  isAdmin(){
    return this.secService.isAdmin();
  }
  onNewEvent(){
    this.router.navigateByUrl("/addEvent");
  }
  onDeleteEvent(e:any){
    let conf=confirm('Etes-vous sure de vouloir supprimer !');
      if(conf)
      this.pubService.deleteEvent(e).subscribe(
        data=>{
          //this.router.navigateByUrl('/events');
        }
      );
  }
  onUpdateEvent(e:any){
    this.router.navigateByUrl("/editEvent/"+e.id);
  }

  participer(e:any){
    this.idevent = e;
    console.log(this.idevent);
    this.auth.getUser().subscribe(data=>{
      this.auteur=data;
      console.log(this.auteur.id); 
    });
    this.pubService.participer(this.idevent,this.auteur.id);
  }
}
