import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnseignatsComponent } from './enseignats.component';

describe('EnseignatsComponent', () => {
  let component: EnseignatsComponent;
  let fixture: ComponentFixture<EnseignatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnseignatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnseignatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
