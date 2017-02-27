when 'To Do', {
    'success' should: 'Done'
}

when 'To Do', {
    'failure' should: 'In Progress'
}

when 'In Progress', {
    'success' should: 'Done'
}

when 'Done', {
    'failure' should: 'In Progress'
}