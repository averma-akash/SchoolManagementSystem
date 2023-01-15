import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleExampleComponent } from './sample-example.component';

describe('SampleExampleComponent', () => {
  let component: SampleExampleComponent;
  let fixture: ComponentFixture<SampleExampleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SampleExampleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SampleExampleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
