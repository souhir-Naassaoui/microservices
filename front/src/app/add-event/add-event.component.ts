import { PublicationsService } from './../publications.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  submitted:any;
  eventFormGroup:any;
  constructor(private formBuilder:FormBuilder,private pubService:PublicationsService) { }

  ngOnInit(): void {
    this.eventFormGroup=this.formBuilder.group({
      title:["",Validators.required],
      date:[new Date(),Validators.required],
      lieux:["",Validators.required],
      
    });
  }
  onSaveEvent(){
    this.submitted=true;
    console.log(this.eventFormGroup.value)
    if(this.eventFormGroup?.invalid) return;
    this.pubService.saveEvent(this.eventFormGroup?.value).subscribe(
      data=>{alert('Operation fait avec success');}
    );
  }

}
