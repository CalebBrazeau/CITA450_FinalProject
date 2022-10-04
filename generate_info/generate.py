# Program to automate generating insert queries for room data
import random as r

bed_sizes = ['Full', 'Queen', 'King']
bools = [True, False]

for i in range(4): # Number of floors
    for x in range(20): # Number of rooms per floor
        room_id = (i + 1) * 100 + x
        number_of_beds = r.randint(1,2)
        bed_1_size = r.choice(bed_sizes)
        bed_2_size = ''
        if(number_of_beds > 1):
            bed_2_size = r.choice(bed_sizes)
        is_handicap_accessible = r.choice(bools)
        has_bathtub = r.choice(bools)
        room_service_id = room_id
        cost_per_night = r.randint(113, 145)
        is_available = True
        is_clean = True
        print(f'INSERT INTO sys.rooms (room_id, number_of_beds, bed_1_size, bed_2_size, is_handicap_accessible, has_bathtub, room_service_id, cost_per_night, is_available, is_clean) VALUES ({room_id}, {number_of_beds}, "{bed_1_size}", "{bed_2_size}", {is_handicap_accessible}, {has_bathtub}, {room_service_id}, {cost_per_night}, {is_available}, {is_clean});')
